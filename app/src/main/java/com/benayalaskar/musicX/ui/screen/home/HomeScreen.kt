package com.benayalaskar.musicX.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.benayalaskar.musicX.MusikRepository
import com.benayalaskar.musicX.ViewModelFactory
import com.benayalaskar.musicX.ui.components.MusicBarSearch
import com.benayalaskar.musicX.ui.components.MusikListItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(MusikRepository())),
    navigateToDetail: (Long) -> Unit,
) {
    val groupedMusic by viewModel.groupedMusic.collectAsState()
    val query by viewModel.query

    Column(modifier = modifier) {
        val listState = rememberLazyListState()
        MusicBarSearch(
            query = query, onQueryChange = viewModel::search,
            modifier = Modifier.background(MaterialTheme.colorScheme.primary)
        )
        LazyColumn(
            modifier = Modifier
                .padding(top = 11.dp)
                .testTag("MusicList"),
            state = listState
        ) {
            groupedMusic.forEach { (init, data) ->
                items(data, key = { it.id }) { dataMusik ->
                    MusikListItem(
                        judul = dataMusik.judul, photoUrl = dataMusik.photoUrl,
                        navigateToDetail = navigateToDetail, musikId = dataMusik.id
                    )
                    Spacer(modifier = Modifier.height(11.dp))
                }
            }

        }

    }
}








