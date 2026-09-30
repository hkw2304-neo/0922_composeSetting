package com.hkw.roomfit.util

import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebViewSetting @Inject constructor(
//    private val _webViewInterface: WebViewInterface
) {
    fun WebViewinit(webView: WebView, webViewInterface: WebViewInterface) {
        webView.apply {
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                useWideViewPort = true
                loadWithOverviewMode = true
                setSupportZoom(false)
                builtInZoomControls = false
                cacheMode = WebSettings.LOAD_DEFAULT
                userAgentString = "$userAgentString RoomfitApp/1.0"

                Log.d("RoomFit_UA", "UserAgent: $userAgentString")
            }
            addJavascriptInterface(webViewInterface, "Android")
            webViewClient = WebViewClient()
        }
    }

}