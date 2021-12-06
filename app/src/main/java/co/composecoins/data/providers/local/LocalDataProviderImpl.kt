package co.composecoins.data.providers.local


import co.composecoins.data.providers.local.database.Database
import co.composecoins.domain.models.responces.gecko.CoinEntity
import javax.inject.Inject

class LocalDataProviderImpl @Inject constructor(private val database: Database) : LocalDataProvider {

    override suspend fun updateCoins(coins: List<CoinEntity>) {
        database.getCoinGeckoDao().updateCoins(coins)
    }

    override suspend fun getAllCoins(): List<CoinEntity> {
        return database.getCoinGeckoDao().getAll()
    }

    override suspend fun getCoinById(id : String): CoinEntity {
        return database.getCoinGeckoDao().getById(id)
    }

    override suspend fun deleteCoins() {
        database.getCoinGeckoDao().deleteAll()
    }
}