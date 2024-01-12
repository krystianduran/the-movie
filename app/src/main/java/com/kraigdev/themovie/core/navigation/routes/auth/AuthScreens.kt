package com.kraigdev.themovie.core.navigation.routes.auth

sealed class AuthScreens(val route: String) {
    data object Login : AuthScreens("login")
}
