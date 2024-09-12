package com.benayalaskar.musicX.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.benayalaskar.musicX.MusikRepository
import com.benayalaskar.musicX.model.Music
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel(private val repository: MusikRepository) : ViewModel() {

    private val _groupedMusik = MutableStateFlow(
        repository.getAllMusik()
            .sortedBy { it.judul }
            .groupBy { it.judul[0] }
    )
    val groupedMusic: MutableStateFlow<Map<Char, List<Music>>> get() = _groupedMusik

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query
    fun search(newQuery: String) {
        _query.value = newQuery
        _groupedMusik.value = repository.searchMusik(_query.value)
            .sortedBy { it.judul }
            .groupBy { it.judul[0] }
    }

}


