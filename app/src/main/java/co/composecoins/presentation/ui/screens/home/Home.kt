package co.composecoins.presentation.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import co.composecoins.R
import co.composecoins.domain.models.responces.gecko.CoinEntity

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    onSelectId : (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val list = viewModel.coinsFlow.collectAsState(initial = listOf())
    val filteredList = remember { mutableStateOf(listOf<CoinEntity>()) }
    filteredList.value = list.value
    viewModel.getCoins()

    val searchState = remember { mutableStateOf(String()) }
    val checkState = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.cardview_dark_background))
            .padding(8.dp),
    ) {
        Row() {
            Text(text = "Full equals")
            Checkbox(
                checked = checkState.value,
                onCheckedChange = {
                    checkState.value = it
                })
        }
        SearchField(text = searchState) { symbol ->
            val newList = if (symbol.isEmpty())
                list.value
            else list.value.filter {
                if (checkState.value)
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
                    ListItem(it = it, onSelectId)
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
fun ListItem(it: CoinEntity, onSelectId: (String) -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        onClick = {onSelectId(it.id)}
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = it.symbol,
                style = TextStyle(color = Color.Black, fontSize = 18.sp),
                modifier = Modifier.width(100.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = it.name,
                style = TextStyle(color = Color.Black, fontSize = 14.sp, textAlign = TextAlign.End)
            )
        }
    }
}