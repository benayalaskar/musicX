package com.benayalaskar.musicX.ui.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benayalaskar.musicX.MusikRepository
import com.benayalaskar.musicX.model.Music
import com.benayalaskar.musicX.model.MusicList
import com.benayalaskar.musicX.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class DetailMusikViewModel(
    private val repository: MusikRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<MusicList>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<MusicList>>
        get() = _uiState

    fun getMusikById(musikId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getMusikById(musikId))
        }
    }


    fun addToFavorite(reward: Music, count: Int) {
        viewModelScope.launch {
            repository.updateMusik(reward.id, count)
        }
    }
}