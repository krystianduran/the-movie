package com.kraigdev.themovie.modules.common.presenter.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.kraigdev.themovie.R

@Preview
@Composable
private fun LogoWithePreview() {
    LogoWitheView()
}

@Composable
fun LogoWitheView(modifier: Modifier = Modifier) {
    val customFontFamily = FontFamily(
        Font(R.font.oswald_regular)
    )

    Text(
        modifier = modifier,
        text = "The Movie",
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 64.sp,
        color = Color.White
    )
}
