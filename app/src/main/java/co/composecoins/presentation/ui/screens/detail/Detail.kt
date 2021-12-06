package co.composecoins.presentation.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

@ExperimentalCoilApi
@Composable
fun DetailScreen(
    id: String,
    viewModel: DetailViewModel = hiltViewModel()
) {
    viewModel.getCoinById(id)
    val coinInfo = viewModel.coinInfo.collectAsState(null)
    coinInfo.value?.let { coin ->
        Column(Modifier.padding(8.dp).fillMaxSize()) {
            Row(horizontalArrangement = Arrangement.Center) {
                Image(
                    painter = rememberImagePainter(data = coin.image.large),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .clip(MaterialTheme.shapes.medium),
                )
                Text(text = coin.name, style = TextStyle(fontSize = 18.sp))
            }
        }
    }
}