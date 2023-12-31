package com.kraigdev.themovie.modules.onboarding.domain

import com.kraigdev.themovie.modules.common.data.Result
import kotlinx.coroutines.flow.Flow

fun interface OnboardingRepository {
    fun savePreferenceOnboarding(): Flow<Result<Unit>>
}
