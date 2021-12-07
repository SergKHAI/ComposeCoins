package co.composecoins.data.providers.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import co.composecoins.data.providers.local.database.daos.CoinGeckoDao
import co.composecoins.data.providers.local.database.daos.FavoritesCoinDao
import co.composecoins.domain.models.responces.gecko.CoinEntity
import co.composecoins.domain.models.responces.gecko.CoinMarketEntity

@Database(
    entities = [
        CoinEntity::class,
        CoinMarketEntity::class
    ],
    exportSchema = false,
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract fun getCoinGeckoDao(): CoinGeckoDao
    abstract fun getFavoritesDao(): FavoritesCoinDao
}