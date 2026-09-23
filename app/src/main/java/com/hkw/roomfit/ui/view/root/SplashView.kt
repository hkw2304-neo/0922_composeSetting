package com.hkw.roomfit.ui.view.root

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.hkw.roomfit.ui.view_model.root.SplashViewModel
import kotlinx.coroutines.delay


@Composable
fun SplashView(
    modifier: Modifier,
    viewModel: SplashViewModel,
    onSplashFinished: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(3000L)
        onSplashFinished()
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF9FAFB)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "RoomFit",
            fontSize = 32.sp,
            color = Color.Black
        )
    }
}
