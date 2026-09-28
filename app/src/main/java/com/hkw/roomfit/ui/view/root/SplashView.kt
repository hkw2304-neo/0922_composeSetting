package com.hkw.roomfit.ui.view.root

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.hkw.roomfit.R
import com.hkw.roomfit.ui.view_model.root.SplashViewModel
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds


@Composable
fun SplashView(
    modifier: Modifier,
    viewModel: SplashViewModel,
    onSplashFinished: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(3000L.milliseconds)
        onSplashFinished()
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.splash_bg)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.roomfit_splash),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}
