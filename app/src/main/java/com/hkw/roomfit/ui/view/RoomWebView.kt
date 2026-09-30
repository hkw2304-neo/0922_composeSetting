package com.hkw.roomfit.ui.view

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.util.Base64
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.hkw.roomfit.R
import com.hkw.roomfit.ui.view_model.RoomWebViewModel
import com.hkw.roomfit.util.WebViewInterface
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.apply

@Composable
fun RoomWebView(
    modifier: Modifier,
    viewModel: RoomWebViewModel,
) {
    val state by viewModel.state.collectAsState()
    val webViewPath = state.webViewPath
    val context: Context = LocalContext.current
    val webView = state.webView
//    val webViews = WebView(context)
//    val webView = remember { WebView(context) }


    val takePictureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            val uri = state.filePath
            if (uri != null) {
                val base64String = createBase64(uri, context)
                if (base64String != null) {
                    webView?.let {
                        webView.evaluateJavascript("window.onImageCaptured('$base64String');") { result ->
                            Log.d("Camera", "test result = $result")
                        }
                    }
//                    webView.evaluateJavascript("window.onImageCaptured('$base64String');") { result ->
//                        Log.d("Camera", "test result = $result")
//                    }
                }
            }
        }
    }

    // 2. 카메라 권한 요청 Launcher
    // 임시 테스트
    // stash 테스트
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            val uri = createImageUri(context)
            viewModel.updateFilePath(uri)
            takePictureLauncher.launch(uri)
        } else {
            Toast.makeText(context, "카메라 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
        }
    }

    // 해당 컴포지션이 불러 질 때 실행
    LaunchedEffect(Unit) {
        Log.d("다시 그리는 중", "다시 그리는 중")
        viewModel.webViewSetting(context)

        viewModel.webViewInterface.onCameraRequest = {
            val hasPermission = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED

            if (hasPermission) {
                val uri = createImageUri(context)
                viewModel.updateFilePath(uri)
                takePictureLauncher.launch(uri)
            } else {
                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }


    Box(
        modifier =
            modifier
                .fillMaxSize()
                .background(
                    colorResource(R.color.teal_200)
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
        if (webView != null) {
            AndroidView(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(R.color.purple_500)),
                factory = {
                    webView.apply {
                        viewModel.webViewInit(webView)
                        loadUrl(webViewPath)
                    }
                }
            )
        }
    }

}

private fun createImageUri(context: Context): Uri {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val imageFileName = "JPEG_${timeStamp}_"
    val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    val imageFile = File.createTempFile(imageFileName, ".jpg", storageDir)

    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileprovider",
        imageFile
    )
}

private fun createBase64(uri: Uri, context: Context): String? {
    try {
        val inputStream = context.contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream?.close()

        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream) // 품질 80%
        val byteArray = outputStream.toByteArray()

        val base64String = Base64.encodeToString(byteArray, Base64.NO_WRAP)

        return "data:image/jpeg;base64,$base64String"
    } catch (e: Exception) {
        Log.e("createBase64", "createBase64 : ${e.toString()}")
        return null
    }
}