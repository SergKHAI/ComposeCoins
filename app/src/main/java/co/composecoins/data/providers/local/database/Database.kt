package co.composecoins.data.providers.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import co.composecoins.data.providers.local.database.daos.CoinGeckoDao
import co.composecoins.domain.models.responces.gecko.CoinEntity

@Database(
    entities = [
        CoinEntity::class
    ],
    exportSchema = false,
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract fun getCoinGeckoDao(): CoinGeckoDao
}