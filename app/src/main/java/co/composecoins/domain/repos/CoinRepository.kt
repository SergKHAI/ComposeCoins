package co.composecoins.domain.repos

import co.composecoins.domain.models.responces.gecko.CoinEntity
import drewcarlson.coingecko.models.coins.CoinFullData

interface CoinRepository {
    suspend fun updateCoinsList()
    suspend fun getLocalCoins(): List<CoinEntity>
    suspend fun getCoinById(id: String): CoinFullData?
}