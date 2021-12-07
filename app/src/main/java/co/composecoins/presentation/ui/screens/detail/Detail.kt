package co.composecoins.presentation.ui.screens.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import drewcarlson.coingecko.models.coins.CoinFullData
import java.text.DecimalFormat
import kotlin.math.roundToInt

val decimalFormat = DecimalFormat("##.####")

@ExperimentalCoilApi
@Composable
fun DetailScreen(
    id: String,
    viewModel: DetailViewModel = hiltViewModel()
) {
    viewModel.getCoinById(id)
    val coinInfo = viewModel.coinInfo.collectAsState(null)
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
    ) {
        coinInfo.value?.let { coin ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp, end = 8.dp)
            ) {
                ImageTitle(coin = coin)
                RowTextInfo(title = "Id", value = coin.id)
                RowTextInfo(title = "Blockchain", value = coin.assetPlatformId)
                RowTextInfo(title = "Symbol", value = coin.symbol)
                val link = coin.links.homepage?.firstOrNull()
                if (link != null)
                    RowTextInfo(title = "Site", value = link) {
                        LinkText(link = link)
                    }
                RowTextInfo(title = "Contract", value = coin.contractAddress)
                val price = coin.marketData?.currentPrice?.getOrDefault("usd", null)
                price?.let {
                    RowTextInfo(title = "Current Price", value = "$it $")
                }
                val volume = coin.marketData?.totalVolume?.getOrDefault("usd", null)
                volume?.let {
                    RowTextInfo(title = "Total Volume", value = "$it $")
                }
                val priceChange24h = coin.marketData?.priceChange24h ?: 0.0
                RowTextInfo(title = "Price changed by 24h", value = priceChange24h.toString()) {
                    PercentageText(price = priceChange24h)
                }
                val priceChangeWeek = coin.marketData?.priceChangePercentage7d ?: 0.0
                RowTextInfo(title = "Price changed by week", value = priceChange24h.toString()) {
                    PercentageText(price = priceChangeWeek)
                }
                val priceChangeYear = coin.marketData?.priceChangePercentage1y ?: 0.0
                RowTextInfo(title = "Price changed by year", value = priceChangeYear.toString()) {
                    PercentageText(price = priceChangeYear)
                }
                Text(modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp, bottom = 8.dp),text = coin.description.getOrDefault("en",""))
            }
        } ?: CircularProgressIndicator(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .align(Alignment.Center),
            color = Color.White,
        )
    }
}

@Composable
fun ImageTitle(coin: CoinFullData) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = rememberImagePainter(data = coin.image.large),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .aspectRatio(1f)
                .clip(CircleShape),
        )
        Text(
            text = coin.name,
            style = TextStyle(fontSize = 24.sp),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun RowTextInfo(
    title: String, value: String?,
    valueText: @Composable RowScope.() -> Unit = {
        if (value != null)
            Text(
                text = value,
                style = TextStyle(textAlign = TextAlign.End),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
    }
) {
    value ?: return
    Column() {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp)
                .fillMaxWidth()
        ) {
            Text(text = title)
            Spacer(modifier = Modifier.width(16.dp))
            valueText()
        }
        Divider(color = Color.White, thickness = 1.dp, modifier = Modifier.padding(top = 4.dp))
    }
}

@Composable
fun PercentageText(price: Double) {
    Text(
        text = "${decimalFormat.format(price)} %",
        style = TextStyle(
            textAlign = TextAlign.End,
            color = when {
                price > 0 -> Color.Green
                (price * 100).roundToInt() == 0 -> Color.White
                else -> Color.Red
            }
        ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun LinkText(link: String) {
    val uriHandler = LocalUriHandler.current
    ClickableText(
        text = AnnotatedString(link),
        style = TextStyle(
            textAlign = TextAlign.End,
            color = Color.Blue,
            textDecoration = TextDecoration.Underline
        ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    ) {
        uriHandler.openUri(link)
    }
}