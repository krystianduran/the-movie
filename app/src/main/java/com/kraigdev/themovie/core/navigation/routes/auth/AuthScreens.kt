package com.kraigdev.themovie.core.navigation.routes.auth

sealed class AuthScreens(val route: String) {
    data object Login : AuthScreens("login")
    data object Register : AuthScreens("register")
    data object EmailVerification : AuthScreens("email_verification")
}
