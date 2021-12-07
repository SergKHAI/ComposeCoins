package co.composecoins.data.providers.local.database.daos

import androidx.room.*
import co.composecoins.domain.models.responces.gecko.CoinMarketEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesCoinDao {
    @Query("DELETE FROM favorites WHERE id = :id")
    suspend fun delete(id: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(coinMarketEntity: CoinMarketEntity)

    @Query("SELECT * FROM favorites")
    fun getAll(): Flow<List<CoinMarketEntity>>

    @Query("SELECT * FROM favorites WHERE id = :id")
    suspend fun getById(id: String): CoinMarketEntity
}