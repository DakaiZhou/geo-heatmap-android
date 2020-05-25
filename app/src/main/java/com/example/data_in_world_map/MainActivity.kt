package com.example.data_in_world_map

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import android.util.Log
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    lateinit var mapWebView: WebView
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mapWebView = findViewById<WebView>(R.id.world_map_web_view)
        mapWebView.settings.javaScriptEnabled = true
        // to access javascript from html page
        mapWebView.addJavascriptInterface(WebAppInterface(this), "Android")
        mapWebView.loadUrl("file:///android_asset/WorldMap.html")


    }

    /** Instantiate the interface and set the context  */
    class WebAppInterface(private val mActivity: Activity) {

        /** get map size  */
        @JavascriptInterface
        fun getMapSize():Pair<Int, Int> {
            // get device dimensions
            var w = 0
            var h = 0
            val displayMetrics = DisplayMetrics()
             mActivity.windowManager.defaultDisplay.getMetrics(displayMetrics)

            val width: Int = displayMetrics.widthPixels
            val height: Int = displayMetrics.heightPixels
            if (width > height) {
                w = width
                h = height
            }
            else {
                w = width
                h = width
            }
            return Pair(w, h)
        }
    }
}
