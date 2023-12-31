package com.kraigdev.themovie.modules.splash.data

import com.kraigdev.themovie.modules.common.data.DomainExceptionRepository
import com.kraigdev.themovie.modules.common.data.GeneralPreferences
import com.kraigdev.themovie.modules.common.data.Result
import com.kraigdev.themovie.modules.splash.domain.SplashRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class SplashRepositoryImpl @Inject constructor(
    private val generalPreferences: GeneralPreferences,
    private val domainExceptionRepository: DomainExceptionRepository
) : SplashRepository {
    override fun showOnboarding(): Flow<Result<Boolean>> = flow<Result<Boolean>> {
        generalPreferences.showOnboarding.first().let { showOnboarding ->
            if (showOnboarding == null) {
                emit(Result.Success(true))
            } else {
                emit(Result.Success(false))
            }
        }
    }.catch {
        emit(Result.Failure(domainExceptionRepository.manageError(it)))
    }
}
