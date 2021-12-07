package co.composecoins.presentation.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import co.composecoins.domain.models.responces.gecko.CoinMarketEntity

@ExperimentalMaterialApi
@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val favoriteState by viewModel.getFavorites().collectAsState(initial = listOf())
    ShowFavorites(list = favoriteState)
}

@Composable
fun ShowFavorites(list: List<CoinMarketEntity>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(list, itemContent = {
                Text(
                    text = it.name,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 25.sp
                )
            })
        })
}
