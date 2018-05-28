package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.test.hex.draftapp.R

class L032Activity : AppCompatActivity() {

    lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l032_activity)

        webView = findViewById(R.id.webView)
        webView.webViewClient = WebViewClient()
        webView.loadUrl(intent.data.toString())
    }
}
