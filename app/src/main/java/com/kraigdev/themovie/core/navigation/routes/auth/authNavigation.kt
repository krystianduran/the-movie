package com.kraigdev.themovie.core.navigation.routes.auth // ktlint-disable filename

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.kraigdev.themovie.core.navigation.AppScreens
import com.kraigdev.themovie.modules.common.presenter.SignInViewModel
import com.kraigdev.themovie.modules.login.LoginScreen

fun NavGraphBuilder.authNavigation(
    navController: NavController
) {
    navigation(
        startDestination = AuthScreens.Login.route,
        route = AppScreens.AuthNavigation.route
    ) {
        composable(AuthScreens.Login.route) {
            val context = LocalContext.current

            val signInViewModel = hiltViewModel<SignInViewModel>()
            val state by signInViewModel.state.collectAsStateWithLifecycle()

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == Activity.RESULT_OK) {
                        signInViewModel.sendResultData(result = result.data)
                    }
                }
            )

            LaunchedEffect(state.signInIntentSender) {
                state.signInIntentSender?.let {
                    launcher.launch(IntentSenderRequest.Builder(it).build())
                }
            }

            LaunchedEffect(state.isSignInSuccessful) {
                if (state.isSignInSuccessful) {
                    Toast.makeText(context, "Sign in successful", Toast.LENGTH_LONG).show()

                    navController.popBackStack()
                    navController.navigate(AppScreens.Main.route)
                    signInViewModel.resetState()
                }
            }

            LoginScreen(
                state = state,
                onGoogleSignInButton = {
                    signInViewModel.signInIntentSender()
                }
            )
        }
    }
}
