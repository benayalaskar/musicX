package com.benayalaskar.musicX.ui.screen.details


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.benayalaskar.musicX.R
import com.benayalaskar.musicX.ViewModelFactory
import com.benayalaskar.musicX.di.Injection
import com.benayalaskar.musicX.ui.common.UiState

@Composable
fun DetailMusikScreen(
    musikId: Long,
    viewModel: DetailMusikViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
    navigateToCart: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getMusikById(musikId)
            }

            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.music.photoUrl,
                    data.music.judul,
                    data.music.desc,
                    count = data.count,
                    onAddToFavorite = { count ->
                        viewModel.addToFavorite(data.music, count)
                        navigateToCart()
                    }
                )
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    image: String,
    title: String,
    desc: String,
    count: Int,
    onAddToFavorite: (count: Int) -> Unit,
) {
    val orderCount by rememberSaveable { mutableStateOf(count) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(61.dp)

    ) {
        AsyncImage(
            model = image, contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .size(201.dp)
                .clip(RoundedCornerShape(17.dp))

        )
        Text(
            text = title,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(6.dp),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 21.sp,
                fontWeight = FontWeight.Medium,

                ),
        )
        Text(
            text = desc,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(5.dp),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 15.sp,
            ),
        )
        if (orderCount > 0) {
            DeleteFavButton(
                text = stringResource(id = R.string.delete_favorite),
                onClick = {
                    onAddToFavorite(orderCount - 1)
                }
            )
        } else {
            FavButton(
                text = stringResource(R.string.add_to_favorite),
                onClick = {
                    onAddToFavorite(orderCount + 1)
                }
            )
        }
    }
}

@Composable
fun FavButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .semantics(mergeDescendants = true) {
                contentDescription = "Favorite Button"
            }
    ) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun DeleteFavButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .semantics(mergeDescendants = true) {
                contentDescription = "Delete Favorite Button"
            }
    ) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}