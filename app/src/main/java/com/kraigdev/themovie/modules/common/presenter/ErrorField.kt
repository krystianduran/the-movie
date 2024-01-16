package com.kraigdev.themovie.modules.common.presenter

data class ErrorField(val isError: Boolean, val message: String) {
    companion object {
        val Empty = ErrorField(false, "")
    }
}
