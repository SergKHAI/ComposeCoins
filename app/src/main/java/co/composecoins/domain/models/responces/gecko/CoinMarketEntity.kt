package co.composecoins.domain.models.responces.gecko

import androidx.room.Entity
import androidx.room.PrimaryKey
import drewcarlson.coingecko.models.coins.CoinMarkets
@Entity(tableName = "favorites")
data class CoinMarketEntity(
    @PrimaryKey
    val id: String,
    val symbol: String,
    val name: String,
    val image: String? = null,
    val currentPrice: Double = 0.0,
    val marketCap: Double = 0.0,
    val marketCapRank: Long = 0,
    val totalVolume: Double = 0.0,
    val high24h: Double = 0.0,
    val low24h: Double = 0.0,
    val priceChange24h: Double = 0.0,
    val priceChangePercentage24h: Double = 0.0,
    val marketCapChange24h: Double = 0.0,
    val marketCapChangePercentage24h: Double = 0.0,
    val circulatingSupply: Double = 0.0,
    val totalSupply: Double? = null,
    val ath: Double = 0.0,
    val atl: Double = 0.0,
    val atlChangePercentage: Double = 0.0,
    val athChangePercentage: Double = 0.0,
    val atlDate: String? = null,
    val athDate: String? = null,
    val lastUpdated: String? = null,
    val priceChangePercentage1hInCurrency: Double = 0.0,
    val fullyDilutedValuation: Long?,
    val maxSupply: Double = 0.0,
)

fun CoinMarkets.toEntity() = CoinMarketEntity(
    id,
    symbol,
    name,
    image,
    currentPrice,
    marketCap,
    marketCapRank,
    totalVolume,
    high24h,
    low24h,
    priceChange24h,
    priceChangePercentage24h,
    marketCapChange24h,
    marketCapChangePercentage24h,
    circulatingSupply,
    totalSupply,
    ath,
    atl,
    atlChangePercentage,
    athChangePercentage,
    atlDate,
    athDate,
    lastUpdated,
    priceChangePercentage1hInCurrency,
    fullyDilutedValuation,
    maxSupply
)