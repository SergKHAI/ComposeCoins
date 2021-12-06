package co.composecoins.data.providers.local.database.daos

import androidx.room.*
import co.composecoins.domain.models.responces.gecko.CoinEntity

@Dao
interface CoinGeckoDao {

    @Transaction
    suspend fun updateCoins(coins: List<CoinEntity>) {
        deleteAll()
        return insertCoins(coins)
    }

    @Query("DELETE FROM coins")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoins(assets: List<CoinEntity>)

    @Query("SELECT * FROM coins")
    suspend fun getAll(): List<CoinEntity>

    @Query("SELECT * FROM coins WHERE id = :id")
    suspend fun getById(id : String): CoinEntity
}