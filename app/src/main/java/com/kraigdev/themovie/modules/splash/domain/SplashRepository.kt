package com.kraigdev.themovie.modules.splash.domain

import com.kraigdev.themovie.modules.common.data.Result
import kotlinx.coroutines.flow.Flow

fun interface SplashRepository {
    fun showOnboarding(): Flow<Result<Boolean>>
}
