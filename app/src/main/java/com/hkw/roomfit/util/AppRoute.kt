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
import com.hkw.roomfit.ui.view.root.SplashView
import com.hkw.roomfit.ui.view_model.RoomWebViewModel
import com.hkw.roomfit.ui.view_model.root.SplashViewModel

object AppRoute {
    const val SPLASHPAGE = "splashpage"
    const val WEBVIEWPAGE = "webviewpage"
}
@Composable
fun NavRoute(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String = AppRoute.SPLASHPAGE,
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
        composable(route = AppRoute.SPLASHPAGE) {
            val splashViewModel: SplashViewModel = hiltViewModel()
            SplashView(
                modifier = modifier,
                viewModel = splashViewModel,
                onSplashFinished = {
                    navController.navigate(AppRoute.WEBVIEWPAGE){
                        // 이전 스택 전부 지우고 다음 화면 스택만 남긴다.
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(route = AppRoute.WEBVIEWPAGE) {
            val roomFitViewModel: RoomWebViewModel = hiltViewModel()
            RoomWebView(
                modifier = modifier,
                viewModel = roomFitViewModel,
            )
        }
    }
}