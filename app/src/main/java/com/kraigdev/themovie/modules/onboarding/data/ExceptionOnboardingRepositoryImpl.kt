package com.kraigdev.themovie.modules.onboarding.data

import com.kraigdev.themovie.modules.common.data.CommonErrors
import com.kraigdev.themovie.modules.common.data.DomainExceptionRepository
import com.kraigdev.themovie.modules.common.domain.DomainException

class ExceptionOnboardingRepositoryImpl : CommonErrors(), DomainExceptionRepository {
    override fun manageError(error: Throwable): DomainException {
        return manageException(error)
    }
}
