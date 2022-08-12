package com.example.myapplication

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //create a webview instance
        val myWebView: WebView = findViewById(R.id.webview)
        //Enable javascript for the webview
        myWebView.settings.setJavaScriptEnabled(true)
        myWebView.settings.javaScriptEnabled = true
        //Associate an interface with the webview to allow communication     //between webview and native app. See communication section
        myWebView.addJavascriptInterface(JSBridge(), "JSBridge")
        myWebView.webViewClient = WebViewClient()
        myWebView.evaluateJavascript(
            "javascript: " + "updateFromNative(\"" + "TEST" + "\")",
            null
        )

        myWebView.loadUrl("https://google.com")
    }

    inner class JSBridge() {
        @JavascriptInterface
        fun showMessageInNative(message: String) {
            Log.e("TAG", message)
        }
    }
}