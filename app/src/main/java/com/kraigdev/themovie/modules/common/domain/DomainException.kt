package com.kraigdev.themovie.modules.common.domain

import java.io.IOException

open class DomainException(override val message: String = "") : Throwable(message)
object InternalErrorException : DomainException()
object UnknownError : DomainException()
object NoConnectivityException : IOException()
object NoConnectivityDomainException : DomainException()
object TimeOutException : DomainException()
object ParseException : DomainException()
