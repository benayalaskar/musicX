package com.benayalaskar.musicX.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.benayalaskar.musicX.R

@Composable
fun AboutScreen(
) {
    val photoUrl =
        "https://media.licdn.com/dms/image/C5603AQE4h3RgEBKTdQ/profile-displayphoto-shrink_800_800/0/1614504687074?e=1705536000&v=beta&t=Q8XXSnhcX3axKZ6ywm_lph9Mg3fQqSqczulHy5pTaNY"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(60.dp)


    ) {
        AsyncImage(
            model = photoUrl, contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .size(200.dp)
                .clip(RoundedCornerShape(16.dp))

        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(5.dp)
                .size(150.dp)
                .shadow(8.dp, RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = null,
                contentScale = ContentScale.Crop, // Ensures the image fills the box
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp))
                    .shadow(8.dp, RoundedCornerShape(8.dp))
            )
        }
        Text(
            text = stringResource(id = R.string.my_name),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(5.dp),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 23.sp,
                fontWeight = FontWeight.Medium,

                ),
        )
        Text(
            text = stringResource(id = R.string.my_email),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(5.dp),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 15.sp,
            ),
        )
    }
}

@Preview
@Composable
fun aboutPreview() {
    AboutScreen()
}