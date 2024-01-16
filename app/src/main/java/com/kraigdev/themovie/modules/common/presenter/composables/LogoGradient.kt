package com.kraigdev.themovie.modules.common.presenter.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Preview
@Composable
private fun LogoPreview() {
    LogoView()
}

@Composable
fun LogoView() {
    val customFontFamily = FontFamily(
        Font(R.font.oswald_regular)
    )
    val gradientColors = listOf(
        FirstGradientColor,
        SecondGradientColor
    )

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
