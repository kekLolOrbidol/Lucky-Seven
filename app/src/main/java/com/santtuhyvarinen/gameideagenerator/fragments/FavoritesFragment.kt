package com.santtuhyvarinen.gameideagenerator.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.santtuhyvarinen.gameideagenerator.IdeaGenerat
import com.santtuhyvarinen.R
import com.santtuhyvarinen.gameideagenerator.adapters.FavoritesAdapter
import com.santtuhyvarinen.gameideagenerator.utils.FavoritesUtill
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment() {

    lateinit var gameIdeaGenerat : IdeaGenerat
    lateinit var favoritesAdapter : FavoritesAdapter

    var favorites = ArrayList<String>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        gameIdeaGenerat = IdeaGenerat(requireContext())

        favorites = FavoritesUtill.loadFavorites(requireContext())

        favoritesAdapter = FavoritesAdapter(requireContext(), favorites)
        favoritesAdapter.favoritesAdapterListener = object : FavoritesAdapter.FavoritesAdapterListener {
            override fun delete(index: Int) {
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle(R.string.favorite_remove_confirmation)
                builder.setPositiveButton(android.R.string.ok, object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        removeFavorite(index)
                    }
                })

                builder.setNegativeButton(android.R.string.cancel, null)

                val dialog = builder.create()
                dialog.show()
            }
        }

        recyclerView.adapter = favoritesAdapter

    }

    private fun removeFavorite(index : Int) {
        favorites.removeAt(index)
        favoritesAdapter.notifyDataSetChanged()

        val success = FavoritesUtill.saveFavorites(requireContext(), favorites)
        if(success) {
            Toast.makeText(requireContext(), getString(R.string.favorite_removed), Toast.LENGTH_SHORT).show()
        }
    }

}