package co.composecoins.data.providers.local

import co.composecoins.domain.models.responces.gecko.CoinEntity

interface LocalDataProvider {
    suspend fun deleteCoins()
    suspend fun getAllCoins(): List<CoinEntity>
    suspend fun updateCoins(coins: List<CoinEntity>)
    suspend fun getCoinById(id: String): CoinEntity
}