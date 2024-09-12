package com.benayalaskar.musicX

import com.benayalaskar.musicX.model.Music
import com.benayalaskar.musicX.model.MusicData
import com.benayalaskar.musicX.model.MusicList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class MusikRepository {

    private val musicList = mutableListOf<MusicList>()

    init {
        if (musicList.isEmpty()) {
            MusicData.music.forEach {
                musicList.add(MusicList(it, 0))
            }
        }
    }

    private fun getMusik(): Flow<List<MusicList>> {
        return flowOf(musicList)
    }

    fun getAllMusik(): List<Music> {
        return MusicData.music
    }

    fun searchMusik(query: String): List<Music> {
        return MusicData.music.filter {
            it.judul.contains(query, ignoreCase = true)
        }

    }

    fun getMusikById(musikId: Long): MusicList {
        return musicList.first {
            it.music.id == musikId
        }
    }

    fun updateMusik(rewardId: Long, newCountValue: Int): Flow<Boolean> {
        val index = musicList.indexOfFirst { it.music.id == rewardId }
        val result = if (index >= 0) {
            val musik = musicList[index]
            musicList[index] =
                musik.copy(music = musik.music, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getFavoriteMusik(): Flow<List<MusicList>> {
        return getMusik()
            .map { orderRewards ->
                orderRewards.filter { orderReward ->
                    orderReward.count != 0
                }
            }
    }

    companion object {
        @Volatile
        private var instance: MusikRepository? = null

        fun getInstance(): MusikRepository =
            instance ?: synchronized(this) {
                MusikRepository().apply {
                    instance = this
                }
            }
    }
}