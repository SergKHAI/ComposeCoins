package co.composecoins.presentation.ui.screens.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.solver.widgets.Optimizer
import androidx.hilt.navigation.compose.hiltViewModel
import co.composecoins.R
import co.composecoins.domain.models.responces.gecko.CoinEntity

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    onSelectId : (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    viewModel.getCoins()
    viewModel.getFavoritesIds()

    val list = viewModel.coinsFlow.collectAsState(initial = listOf())
    val filteredList = viewModel.flist
    if (filteredList.value.isEmpty())
        filteredList.value = list.value
    val favoritesList = viewModel.favoritesFlow.collectAsState(initial = listOf())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.black))
            .padding(8.dp),
    ) {
        Row() {
            Text(text = "Full equals")
            Checkbox(
                checked = viewModel.checkState.value,
                colors = CheckboxDefaults.colors(Color.White, Color.White, Color.Black),
                onCheckedChange = {
                    viewModel.checkState.value = it
                })
        }
        SearchField(text = viewModel.searchState) { symbol ->
            val newList = if (symbol.isEmpty())
                list.value
            else list.value.filter {
                if (viewModel.checkState.value)
                    it.symbol.lowercase() == symbol.lowercase()
                else
                    it.symbol.lowercase().contains(symbol.lowercase())
            }
            filteredList.value = newList
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                items(filteredList.value, itemContent = {
                    ListItem(
                        it = it,
                        isFavorite = it.id in favoritesList.value,
                        onSelectId = onSelectId,
                        onFavoriteClick = {
                            val id = it.id
                            if (id in favoritesList.value)
                                viewModel.removeFavorite(id)
                            else
                                viewModel.addFavorite(id)
                        })
                })
            })
    }
}

@Composable
private fun SearchField(text: MutableState<String>, onTextChanged: (String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
            .background(Color.Transparent),
        textStyle = TextStyle(Color.White),
        value = text.value,
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Color.White,
            focusedLabelColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.LightGray,
            unfocusedLabelColor = Color.White
        ),
        label = { Text("Search") },
        onValueChange = {
            text.value = it
            onTextChanged(it)
        }
    )
}

@ExperimentalMaterialApi
@Composable
fun ListItem(
    it: CoinEntity,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onSelectId: (String) -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        onClick = { onSelectId(it.id) },
        modifier = Modifier.fillMaxWidth()
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth(),
            optimizationLevel = Optimizer.OPTIMIZATION_STANDARD
        ) {
            val (iconButton, column, image) = createRefs()
            IconButton(onClick = onFavoriteClick,
                modifier = Modifier.constrainAs(iconButton) {
                    top.linkTo(parent.top, 8.dp)
                    start.linkTo(parent.start, 8.dp)
                    bottom.linkTo(parent.bottom, 8.dp)
                }) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = animateColorAsState(
                        when {
                            isFavorite -> Color.Yellow
                            else -> Color.Black
                        }
                    ).value,
                )
            }
            Column(Modifier.constrainAs(column) {
                top.linkTo(parent.top)
                start.linkTo(iconButton.end, 8.dp)
                end.linkTo(image.start, 8.dp)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
            }) {
                Text(
                    text = it.symbol,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = it.name,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start
                    ),
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                )
            }
            Image(
                modifier = Modifier
                    .width(25.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end, 8.dp)
                        bottom.linkTo(parent.bottom)
                    },
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = null
            )
        }
    }
}