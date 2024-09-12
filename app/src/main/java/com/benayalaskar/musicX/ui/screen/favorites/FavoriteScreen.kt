package com.benayalaskar.musicX.ui.screen.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.benayalaskar.musicX.R
import com.benayalaskar.musicX.ViewModelFactory
import com.benayalaskar.musicX.di.Injection
import com.benayalaskar.musicX.ui.common.UiState
import com.benayalaskar.musicX.ui.components.FavMusicListItem

@Composable
fun FavoriteScreen(
    viewModel: FavViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateToDetail: (Long) -> Unit,


    ) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAddedFavorite()
            }

            is UiState.Success -> {
                FavoriteContent(
                    uiState.data, navigateToDetail = navigateToDetail
                )
            }

            is UiState.Error -> {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteContent(
    state: FavState,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,

    ) {

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.menu_favorite),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 13.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 19.sp,
                    textAlign = TextAlign.Center
                )
            }
        )
        if (state.musicList.isEmpty()) {
            Text(
                stringResource(id = R.string.favorite_kosong),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            LazyColumn(
                contentPadding = PaddingValues(17.dp),
                verticalArrangement = Arrangement.spacedBy(9.dp),
                modifier = Modifier.weight(weight = 1f)
            ) {


                items(state.musicList, key = { it.music.id }) { data ->

                    FavMusicListItem(
                        judul = data.music.judul, photoUrl = data.music.photoUrl,
                        musikId = data.music.id, navigateToDetail = navigateToDetail,
                    )

                }
            }
        }
    }
}
