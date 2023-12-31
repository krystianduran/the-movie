package com.kraigdev.themovie.core.di.onboarding

import com.kraigdev.themovie.modules.common.data.GeneralPreferences
import com.kraigdev.themovie.modules.onboarding.data.ExceptionOnboardingRepositoryImpl
import com.kraigdev.themovie.modules.onboarding.data.OnboardingRepositoryImpl
import com.kraigdev.themovie.modules.onboarding.domain.OnboardingRepository
import com.kraigdev.themovie.modules.splash.domain.useCases.SavePreferenceOnboardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object OnboardingModule {

    @Provides
    @ViewModelScoped
    fun provideSavePreferenceOnboardingUseCase(
        onboardingRepository: OnboardingRepository
    ) = SavePreferenceOnboardingUseCase(
        onboardingRepository = onboardingRepository
    )

    @Provides
    @ViewModelScoped
    fun provideOnboardingRepository(
        generalPreferences: GeneralPreferences
    ): OnboardingRepository = OnboardingRepositoryImpl(
        generalPreferences = generalPreferences,
        domainExceptionRepository = ExceptionOnboardingRepositoryImpl()
    )
}
