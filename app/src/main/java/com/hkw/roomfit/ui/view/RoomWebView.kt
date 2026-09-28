package com.hkw.roomfit.ui.view

import android.content.Context
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.hkw.roomfit.R
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
                    colorResource(R.color.white)
                ),
    ) {
//        Text(
//            "안녕!!!",
//            style = MaterialTheme.typography.bodyMedium.copy(
//                color = Color.Red,
//                fontSize = 20.sp,
//            )
            // 이렇게 하면 폰트 다시 설정됨
//            style = TextStyle(
//                fontWeight = FontWeight.Normal,
//                fontSize = 50.sp
//            )
//        )
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