package com.hkw.roomfit.ui.view_model

import android.content.Context
import android.webkit.WebView
import androidx.lifecycle.ViewModel
import com.hkw.roomfit.BuildConfig
import com.hkw.roomfit.util.WebViewSetting
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class RoomWebViewState(
    val webViewPath: String = ""
)

@HiltViewModel
class RoomWebViewModel @Inject constructor(
    private val webViewInit : WebViewSetting,
) : ViewModel(){

    private val _roomWebViewState = MutableStateFlow(RoomWebViewState())
    val roomWebViewState = _roomWebViewState.asStateFlow()

    init{
        val webViewPath = BuildConfig.WEBVIEWPATH
        _roomWebViewState.update {
            it.copy(
                webViewPath = webViewPath
            )
        }
    }
    fun webViewInit(webView: WebView){
        webViewInit.WebViewinit(webView)
    }
}