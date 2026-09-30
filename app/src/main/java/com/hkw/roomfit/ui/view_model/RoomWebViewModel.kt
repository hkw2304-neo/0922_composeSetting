package com.hkw.roomfit.ui.view_model

import android.content.Context
import android.net.Uri
import android.util.Log
import android.webkit.WebView
import androidx.lifecycle.ViewModel
import com.hkw.roomfit.BuildConfig
import com.hkw.roomfit.util.PreferenceManager
import com.hkw.roomfit.util.WebViewInterface
import com.hkw.roomfit.util.WebViewSetting
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class RoomWebViewState(
    val webViewPath: String = "",
    val filePath: Uri? = null,
    val webView: WebView? = null
//    val sampleList: List<String> = emptyList(),
//    val sampleArr: List<String> = emptyList(),
//    val sampleArr2: List<String> = listOf("001_1","001_2","001_3"),
//    val sampleArr3: List<String> = arrayListOf("002_1","002_2","002_3")
)

@HiltViewModel
class RoomWebViewModel @Inject constructor(
    private val webViewInit : WebViewSetting,
    private val pref: PreferenceManager,
    val webViewInterface : WebViewInterface
) : ViewModel(){

    private val _mainViewModel: String = "MainViewModel"
    private val _state = MutableStateFlow(RoomWebViewState())
    val state = _state.asStateFlow()

    init{
        Log.d("RoomWebViewModel init","RoomWebViewModel init")
//        val tempArr: List<String> = listOf("001_1", "001_2", "003 003", " 004 004 ")
        pref.setName("kiwon")
        val webViewPath = BuildConfig.WEBVIEWPATH
        _state.update {
            it.copy(
                webViewPath = webViewPath,
//                sampleList = it.sampleList + pref.getName(),
//                sampleArr = tempArr,
            )
        }

        /*
        _state.value.sampleArr.forEach {
            Log.d(_mainViewModel, it)
        }

        Log.d(_mainViewModel, "++++++++++++")

        var totalItem = ""
        val item = _state.value.sampleArr.mapIndexed { index, value ->
            /*
            if (index == 3) {
//                value.replace("001","change")
//                value.replace(" ", "")
//                value.substring(0,3)
                value.trim()
            } else {
                value
            }
            */
            "_prefix_" + value + "_subfix_"
        }
        item.forEach {
            totalItem += it
        }
        _state.update {
            it.copy(
                sampleArr = item
            )
        }
        _state.value.sampleArr.forEach {
            Log.d(_mainViewModel, it)
        }
        Log.d(_mainViewModel, totalItem)
         */
    }

    fun webViewSetting(context: Context){
        val webView = WebView(context)
          _state.update{
              it.copy(
                  webView = webView
              )
          }
    }

    fun webViewInit(webView: WebView){
        webViewInit.WebViewinit(webView, webViewInterface)
    }
    fun updateFilePath(filePath: Uri){
        _state.update {
            it.copy(
                filePath = filePath
            )
        }
    }
}