package com.kraigdev.themovie.core.navigation

sealed class AppScreens(val route: String) {
    data object Splash : AppScreens("splash")
    data object Main : AppScreens("main")
}
