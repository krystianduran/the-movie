package com.kraigdev.themovie.core.navigation.routes.auth // ktlint-disable filename

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.kraigdev.themovie.core.navigation.AppScreens
import com.kraigdev.themovie.modules.common.presenter.SignInViewModel
import com.kraigdev.themovie.modules.common.presenter.SignUpViewModel
import com.kraigdev.themovie.modules.login.LoginScreen

fun NavGraphBuilder.authNavigation(
    navController: NavController
) {
    navigation(
        startDestination = AuthScreens.Login.route,
        route = AppScreens.AuthNavigation.route
    ) {
        composable(AuthScreens.Login.route) {
            val signInViewModel = hiltViewModel<SignInViewModel>()
            val signInState by signInViewModel.state.collectAsStateWithLifecycle()

            val signUpViewModel = hiltViewModel<SignUpViewModel>()
            val signUpState by signUpViewModel.state.collectAsStateWithLifecycle()

            ValidateStates(
                signInViewModel = signInViewModel,
                navController = navController,
                signInState = signInState,
                signUpState = signUpState,
                signUpViewModel = signUpViewModel
            )

            LoginScreen(
                state = signInState,
                onLoginUser = { email, password ->
                    signInViewModel.onSignInWithEmailResult(
                        email = email,
                        password = password
                    )
                },
                onCreateUser = { email, password ->
                    signUpViewModel.onSignUp(
                        email = email,
                        password = password
                    )
                },
                onGoogleSignInButton = {
                    signInViewModel.signInIntentSender()
                }
            )
        }

        composable(AuthScreens.EmailVerification.route) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = "Email verification")
            }
        }
    }
}

@Composable
private fun ValidateStates(
    signInViewModel: SignInViewModel,
    navController: NavController,
    signInState: SignInViewModel.SignInState,
    signUpState: SignUpViewModel.SignUpState,
    signUpViewModel: SignUpViewModel
) {
    val context = LocalContext.current

    ValidateLauncher(signInViewModel = signInViewModel, signInState = signInState)

    LaunchedEffect(Unit) {
        if (signInViewModel.getSignInUser() != null) {
            navController.popBackStack()
            navController.navigate(AppScreens.Main.route)
        }
    }

    LaunchedEffect(signInState.isSignInSuccessful) {
        if (signInState.isSignInSuccessful) {
            Toast.makeText(context, "Sign in successful", Toast.LENGTH_LONG).show()

            navController.popBackStack()
            navController.navigate(AppScreens.Main.route)
            signInViewModel.resetState()
        }
    }

    LaunchedEffect(signUpState.isSignUpSuccessful) {
        if (signUpState.isSignUpSuccessful) {
            val user = signInViewModel.getCurrentUser()
            if (user != null) {
                Log.d("AuthNavigation", "User: ${user.isEmailVerified}")
                if (user.isEmailVerified) {
                    // Validate user data
                } else {
                    navController.navigate(AuthScreens.EmailVerification.route)
                    signUpViewModel.resetState()
                }
            }
        }
    }
}

@Composable
private fun ValidateLauncher(
    signInViewModel: SignInViewModel,
    signInState: SignInViewModel.SignInState
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                signInViewModel.sendResultData(result = result.data)
            }
        }
    )

    LaunchedEffect(signInState.signInIntentSender) {
        signInState.signInIntentSender?.let {
            launcher.launch(IntentSenderRequest.Builder(it).build())
        }
    }
}
