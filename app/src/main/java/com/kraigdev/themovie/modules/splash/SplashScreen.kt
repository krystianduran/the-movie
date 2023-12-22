package com.kraigdev.themovie.modules.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.kraigdev.themovie.R
import com.kraigdev.themovie.ui.theme.FirstGradientColor
import com.kraigdev.themovie.ui.theme.SecondGradientColor
import com.kraigdev.themovie.ui.theme.SplashBackground
import kotlinx.coroutines.delay

private const val SPLASH_DELAY = 3000L

@Composable
fun SplashScreen(onNavigate: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(SPLASH_DELAY)
        onNavigate()
    }

    SplashScreenView()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SplashScreenPreview() {
    SplashScreen(onNavigate = {})
}

@Composable
private fun SplashScreenView() {
    val customFontFamily = FontFamily(
        Font(R.font.oswald_regular)
    )
    val gradientColors = listOf(
        FirstGradientColor,
        SecondGradientColor
    )

    Box(
        modifier = Modifier.fillMaxSize()
            .background(color = SplashBackground),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "The Movie",
            style = TextStyle(
                brush = Brush.linearGradient(
                    colors = gradientColors
                )
            ),
            fontFamily = customFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 64.sp,
            color = Color.White
        )
    }
}
