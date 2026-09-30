package com.hkw.roomfit.util

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.webkit.JavascriptInterface
import android.widget.Toast
import com.hkw.roomfit.ui.view_model.RoomWebViewState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebViewInterface @Inject constructor(
    @ApplicationContext val context: Context
) {

    // 웹뷰용 함수는 백그라운드에서 실행
    // 브릿지 매개변수 규칙은 코틀린에서만 적용 웹에서는 반드시 같이 넘겨줘야함

    // 실행 함수만 선안하고 뷰단에서 초기화
    var onCameraRequest:(() -> Unit)? = null

    @JavascriptInterface
    fun openToast(message: String="hello world~!~!~!~!") {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    @JavascriptInterface
    fun openCamera(){
        // 카메라 기능은 Activity or Composable 위에서 실행
        onCameraRequest?.invoke()
    }
}