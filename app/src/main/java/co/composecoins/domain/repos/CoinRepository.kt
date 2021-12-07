package co.composecoins.domain.repos

import co.composecoins.domain.models.responces.gecko.CoinEntity
import co.composecoins.domain.models.responces.gecko.CoinMarketEntity
import drewcarlson.coingecko.models.coins.CoinFullData
import kotlinx.coroutines.flow.Flow

interface CoinRepository {
    suspend fun updateCoinsList()
    suspend fun getLocalCoins(): List<CoinEntity>
    suspend fun getCoinById(id: String): CoinFullData?
    suspend fun addFavorite(id: String)
    suspend fun deleteFavorite(id: String)
    fun getFavorites(): Flow<List<CoinMarketEntity>>
}