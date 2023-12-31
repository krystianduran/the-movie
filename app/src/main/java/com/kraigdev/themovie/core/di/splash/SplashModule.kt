package com.kraigdev.themovie.core.di.splash

import com.kraigdev.themovie.modules.common.data.GeneralPreferences
import com.kraigdev.themovie.modules.splash.data.ExceptionSplashRepositoryImpl
import com.kraigdev.themovie.modules.splash.data.SplashRepositoryImpl
import com.kraigdev.themovie.modules.splash.domain.SplashRepository
import com.kraigdev.themovie.modules.splash.domain.useCases.ShowOnboardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object SplashModule {

    @Provides
    @ViewModelScoped
    fun provideShowOnboardingUseCase(
        splashRepository: SplashRepository
    ) = ShowOnboardingUseCase(
        splashRepository = splashRepository
    )

    @Provides
    @ViewModelScoped
    fun provideSplashRepository(
        generalPreferences: GeneralPreferences
    ): SplashRepository = SplashRepositoryImpl(
        generalPreferences = generalPreferences,
        domainExceptionRepository = ExceptionSplashRepositoryImpl()
    )
}
