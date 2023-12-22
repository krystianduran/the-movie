package com.kraigdev.themovie.modules.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainScreen() {
    MainScreenView()
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()
}

@Composable
private fun MainScreenView() {
    Text(text = "Main Screen")
}
