package com.kraigdev.themovie.modules.onboarding

import androidx.annotation.DrawableRes
import com.kraigdev.themovie.R

sealed class OnBoardingPage(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
) {
    data object First : OnBoardingPage(
        image = R.drawable.first_image,
        title = "First Page",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit 1."
    )

    data object Second : OnBoardingPage(
        image = R.drawable.second_image,
        title = "Second Page",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit 2."
    )

    data object Third : OnBoardingPage(
        image = R.drawable.third_image,
        title = "Third Page",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit 3."
    )
}
