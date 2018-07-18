package com.test.hex.draftapp.numbered

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebViewClient
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l032_activity.*

class L032Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l032_activity)

        webView.webViewClient = WebViewClient()
        webView.loadUrl(intent.data.toString())
    }
}
