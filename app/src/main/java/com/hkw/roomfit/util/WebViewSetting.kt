package com.hkw.roomfit.util

import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebViewSetting @Inject constructor(){
    fun WebViewinit(webView: WebView) {
        webView.apply {
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                useWideViewPort = true
                loadWithOverviewMode = true
                setSupportZoom(false)
                builtInZoomControls = false
                cacheMode = WebSettings.LOAD_DEFAULT
            }
            webViewClient = WebViewClient()
        }
    }

}