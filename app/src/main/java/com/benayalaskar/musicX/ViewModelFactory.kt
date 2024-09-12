package com.benayalaskar.musicX

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.benayalaskar.musicX.ui.screen.details.DetailMusikViewModel
import com.benayalaskar.musicX.ui.screen.favorites.FavViewModel
import com.benayalaskar.musicX.ui.screen.home.HomeViewModel


class ViewModelFactory(private val repository: MusikRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(DetailMusikViewModel::class.java)) {
            return DetailMusikViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(FavViewModel::class.java)) {
            return FavViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
