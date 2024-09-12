package com.benayalaskar.musicX.ui.screen.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benayalaskar.musicX.MusikRepository
import com.benayalaskar.musicX.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavViewModel(
    private val repository: MusikRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<FavState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<FavState>>
        get() = _uiState

    fun getAddedFavorite() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getFavoriteMusik()
                .collect { musik ->
                    _uiState.value = UiState.Success(FavState(musik))
                }
        }
    }

}