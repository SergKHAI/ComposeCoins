package co.composecoins.data.providers.local

import co.composecoins.domain.models.responces.gecko.CoinEntity
import co.composecoins.domain.models.responces.gecko.CoinMarketEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataProvider {
    suspend fun deleteCoins()
    suspend fun getAllCoins(): List<CoinEntity>
    suspend fun updateCoins(coins: List<CoinEntity>)
    suspend fun getCoinById(id: String): CoinEntity
    fun getFavorites(): Flow<List<CoinMarketEntity>>
    suspend fun deleteFavorite(id: String)
    suspend fun addFavorite(coinMarketEntity: CoinMarketEntity)
}