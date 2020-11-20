package com.santtuhyvarinen.gameideagenerator.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import java.io.IOException
import kotlin.reflect.typeOf


class FavoritesUtill {

    companion object {

        const val FAVORITES_KEY = "favorites"

        fun loadFavorites(context: Context) : ArrayList<String> {
            val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            val gson = Gson()
            var favorites = ArrayList<String>()
            try {
                val json = prefs.getString(FAVORITES_KEY, "")
                favorites = gson.fromJson(json, ArrayList<String>().javaClass)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return favorites
        }

        fun saveFavorites(context: Context, favorites : ArrayList<String>) : Boolean {
            val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

            val gson = Gson()
            try {
                val json = gson.toJson(favorites)
                prefs.edit().putString(FAVORITES_KEY, json).apply()
                return true
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return false
        }
    }
}