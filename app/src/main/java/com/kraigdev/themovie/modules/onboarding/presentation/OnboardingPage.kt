package com.kraigdev.themovie.modules.onboarding.presentation

import androidx.annotation.DrawableRes
import com.kraigdev.themovie.R

sealed class OnboardingPage(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
) {
    data object First : OnboardingPage(
        image = R.drawable.first_image,
        title = "First Page",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit 1."
    )

    data object Second : OnboardingPage(
        image = R.drawable.second_image,
        title = "Second Page",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit 2."
    )

    data object Third : OnboardingPage(
        image = R.drawable.third_image,
        title = "Third Page",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit 3."
    )
}
