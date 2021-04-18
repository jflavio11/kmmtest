package com.jflavio.kmmtest.android.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jflavio.kmmtest.RocketLaunch
import com.jflavio.kmmtest.shared.SpaceXRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * MainViewModel
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since  17/04/2021
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: SpaceXRepository
) : ViewModel() {

    private val _state = mutableStateOf<MainViewState>(MainViewState.Default)
    val state: MutableState<MainViewState> = _state

    fun getLaunches() {
        viewModelScope.launch {
            try {
                _state.value = MainViewState.Success(repository.getLaunches(true))
            } catch (e: Error) {
                _state.value = MainViewState.Error(e.message.orEmpty())
            }
        }
    }

    sealed class MainViewState {
        object Default : MainViewState()
        data class Error(val message: String) : MainViewState()
        data class Success(val list: List<RocketLaunch>) : MainViewState()
    }

}