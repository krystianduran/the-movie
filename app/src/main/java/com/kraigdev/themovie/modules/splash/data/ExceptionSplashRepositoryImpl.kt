package com.kraigdev.themovie.modules.splash.data

import com.kraigdev.themovie.modules.common.data.CommonErrors
import com.kraigdev.themovie.modules.common.data.DomainExceptionRepository
import com.kraigdev.themovie.modules.common.domain.DomainException

class ExceptionSplashRepositoryImpl : CommonErrors(), DomainExceptionRepository {
    override fun manageError(error: Throwable): DomainException {
        return manageException(error)
    }
}
