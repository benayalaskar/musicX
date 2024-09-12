package com.benayalaskar.musicX.di

import com.benayalaskar.musicX.MusikRepository

object Injection {
    fun provideRepository(): MusikRepository {
        return MusikRepository.getInstance()
    }
}