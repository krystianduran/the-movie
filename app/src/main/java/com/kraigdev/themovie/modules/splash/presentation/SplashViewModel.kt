package com.kraigdev.themovie.modules.splash.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kraigdev.themovie.modules.common.data.fold
import com.kraigdev.themovie.modules.splash.domain.useCases.ShowOnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val showOnboardingUseCase: ShowOnboardingUseCase,
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    private var _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    fun showOnboarding() {
        showOnboardingUseCase().map { result ->
            result.fold(
                onSuccess = {
                    _state.value = _state.value.copy(loading = false, showOnboarding = it)
                },
                onFailure = {
                    Log.e("SplashViewModel", "validateShowOnboarding: ${it.message}")
                    _state.value = _state.value.copy(loading = false, error = it.message)
                }
            )
        }.onStart {
            _state.value = _state.value.copy(loading = true)
        }.flowOn(coroutineDispatcher).launchIn(viewModelScope)
    }

    data class UiState(
        val loading: Boolean = false,
        val showOnboarding: Boolean? = null,
        val error: String? = null
    )
}
