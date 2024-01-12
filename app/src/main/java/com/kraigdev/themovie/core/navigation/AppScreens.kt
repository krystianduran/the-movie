package com.kraigdev.themovie.core.navigation

sealed class AppScreens(val route: String) {
    data object Splash : AppScreens("splash")
    data object Onboarding : AppScreens("onboarding")
    data object AuthNavigation : AppScreens("auth_navigation")
    data object Main : AppScreens("main")
}
