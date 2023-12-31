package com.kraigdev.themovie.modules.common.data

import com.kraigdev.themovie.modules.common.domain.DomainException

fun interface DomainExceptionRepository {
    fun manageError(error: Throwable): DomainException
}
