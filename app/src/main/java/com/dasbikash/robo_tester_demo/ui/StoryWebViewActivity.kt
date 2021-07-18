package com.dasbikash.robo_tester_demo.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.URLUtil
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import com.dasbikash.robo_tester_demo.R
import kotlinx.android.synthetic.main.activity_web_view.*

class StoryWebViewActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        init()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun init() {
        web_view.settings.javaScriptEnabled = true
        web_view.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return false // then it is not handled by default action
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageCommitVisible(view: WebView?, url: String?) {
                super.onPageCommitVisible(view, url)
                progressBar.isVisible = false
            }
        }

        btnBack.setOnClickListener { finish() }

        if (intent.hasExtra(EXTRA_URL)){
            intent.getStringExtra(EXTRA_URL)?.let {
                web_view.loadUrl(it)
                etAddressBar.setText(it)
                progressBar.isVisible = true
            }
        }else finish()
    }

    companion object{
        private const val EXTRA_URL = "com.dasbikash.robo_tester_demo.ui.StoryWebViewActivity.EXTRA_URL"

        fun getIntent(context: Context,url:String):Intent? =
            if (URLUtil.isNetworkUrl(url)){
                Intent(context,StoryWebViewActivity::class.java).apply {
                    putExtra(EXTRA_URL,url)
                }
            }else null
    }
}