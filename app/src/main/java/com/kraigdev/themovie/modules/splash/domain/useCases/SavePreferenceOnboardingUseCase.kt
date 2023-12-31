package com.kraigdev.themovie.modules.splash.domain.useCases

import com.kraigdev.themovie.modules.onboarding.domain.OnboardingRepository
import javax.inject.Inject

class SavePreferenceOnboardingUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {
    operator fun invoke() = onboardingRepository.savePreferenceOnboarding()
}
