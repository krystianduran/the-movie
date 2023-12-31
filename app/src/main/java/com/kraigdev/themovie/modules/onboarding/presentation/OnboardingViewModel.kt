package com.kraigdev.themovie.modules.onboarding.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kraigdev.themovie.modules.common.data.fold
import com.kraigdev.themovie.modules.splash.domain.useCases.SavePreferenceOnboardingUseCase
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
class OnboardingViewModel @Inject constructor(
    private val savePreferenceOnboardingUseCase: SavePreferenceOnboardingUseCase,
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    private var _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    fun savePreferenceOnboarding() {
        savePreferenceOnboardingUseCase().map { result ->
            result.fold(
                onSuccess = {
                    _state.value = _state.value.copy(loading = false, success = true)
                },
                onFailure = {
                    _state.value = _state.value.copy(loading = false, error = it.message)
                }
            )
        }.onStart {
            _state.value = _state.value.copy(loading = true)
        }.flowOn(coroutineDispatcher).launchIn(viewModelScope)
    }

    data class UiState(
        val loading: Boolean = false,
        val success: Boolean = false,
        val error: String? = null
    )
}
