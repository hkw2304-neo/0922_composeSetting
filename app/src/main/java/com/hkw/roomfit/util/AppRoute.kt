package com.hkw.roomfit.util

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hkw.roomfit.ui.view.RoomWebView
import com.hkw.roomfit.ui.view_model.RoomWebViewModel

object AppRoute {
    const val WEBVIEWPAGE = "webviewpage"
}
@Composable
fun NavRoute(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String = AppRoute.WEBVIEWPAGE,
){
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300),
            ) + fadeIn(animationSpec = tween(300))
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300),
            ) + fadeOut(animationSpec = tween(300))
        },
    ){
        composable(route = AppRoute.WEBVIEWPAGE) {
            val roomFitViewModel: RoomWebViewModel = hiltViewModel()
            RoomWebView(
                modifier = modifier,
                viewModel = roomFitViewModel,
            )
        }
    }
}