package co.composecoins.data.providers.network

import co.composecoins.domain.models.responces.gecko.CoinEntity
import co.composecoins.domain.models.responces.gecko.CoinMarketEntity
import co.composecoins.domain.models.responces.gecko.toEntity
import drewcarlson.coingecko.CoinGeckoClient
import drewcarlson.coingecko.models.coins.CoinFullData
import drewcarlson.coingecko.models.coins.CoinMarkets
import drewcarlson.coingecko.models.coins.CoinMarketsList
import javax.inject.Inject
import javax.inject.Singleton

class NetworkDataProvider @Inject constructor(private val coinGeckoClient: CoinGeckoClient) {
    suspend fun getCoins() : List<CoinEntity>? {
        return try {
            coinGeckoClient.getCoinList().map { it.toEntity() }
        } catch (e : java.lang.Exception){
            null
        }
    }

    suspend fun getCoinInfo(id: String): CoinFullData? {
        return try {
            coinGeckoClient.getCoinById(id, marketData = true)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getMarketCoinInfo(id: String): CoinMarketsList? {
        return try {
            coinGeckoClient.getCoinMarkets("usd", id)
        } catch (e: Exception) {
            null
        }
    }

}