package com.kraigdev.themovie.modules.splash.domain.useCases

import com.kraigdev.themovie.modules.splash.domain.SplashRepository
import javax.inject.Inject

class ShowOnboardingUseCase @Inject constructor(
    private val splashRepository: SplashRepository
) {
    operator fun invoke() = splashRepository.showOnboarding()
}
