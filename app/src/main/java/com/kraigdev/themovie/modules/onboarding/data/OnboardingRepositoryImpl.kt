package com.kraigdev.themovie.modules.onboarding.data

import com.kraigdev.themovie.modules.common.data.DomainExceptionRepository
import com.kraigdev.themovie.modules.common.data.GeneralPreferences
import com.kraigdev.themovie.modules.common.data.Result
import com.kraigdev.themovie.modules.onboarding.domain.OnboardingRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class OnboardingRepositoryImpl @Inject constructor(
    private val generalPreferences: GeneralPreferences,
    private val domainExceptionRepository: DomainExceptionRepository
) : OnboardingRepository {

    override fun savePreferenceOnboarding(): Flow<Result<Unit>> =
        flow<Result<Unit>> {
            generalPreferences.saveShowOnboarding()
            emit(Result.Success(Unit))
        }.catch {
            emit(Result.Failure(domainExceptionRepository.manageError(it)))
        }
}
