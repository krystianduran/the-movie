package com.kraigdev.themovie.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kraigdev.themovie.modules.main.MainScreen
import com.kraigdev.themovie.modules.splash.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.Splash.route
    ) {
        composable(AppScreens.Splash.route) {
            SplashScreen(
                onNavigate = {
                    navController.popBackStack()
                    navController.navigate(AppScreens.Main.route)
                }
            )
        }
        composable(AppScreens.Main.route) {
            MainScreen()
        }
    }
}
