package com.hkw.roomfit.ui.view

import android.content.Context
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.hkw.roomfit.ui.view_model.RoomWebViewModel
import kotlin.apply

@Composable
fun RoomWebView(
    modifier: Modifier,
    viewModel: RoomWebViewModel
) {
    val state by viewModel.roomWebViewState.collectAsState()
    val webViewPath = state.webViewPath
    val context: Context = LocalContext.current
    val webView = WebView(context)


    Box(
        modifier =
            modifier
                .fillMaxSize()
                .background(
                    Color(
                        0xFFF9FAFB,
                    ),
                ),
    ) {
        AndroidView(
            modifier = modifier,
            factory = {
                webView.apply {
                    viewModel.webViewInit(webView)
                    loadUrl(webViewPath)
                }

            }
        )
    }

}