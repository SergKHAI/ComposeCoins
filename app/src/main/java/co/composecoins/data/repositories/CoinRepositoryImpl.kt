package co.composecoins.data.repositories

import co.composecoins.data.providers.local.LocalDataProvider
import co.composecoins.data.providers.network.NetworkDataProvider
import co.composecoins.domain.models.responces.gecko.toEntity
import co.composecoins.domain.repos.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val localDataProvider: LocalDataProvider,
    private val networkDataProvider: NetworkDataProvider
) : CoinRepository {
    override suspend fun updateCoinsList(){
        val list = networkDataProvider.getCoins()
        localDataProvider.updateCoins(list ?: return)
    }

    override suspend fun getLocalCoins() = localDataProvider.getAllCoins()

    override suspend fun getCoinById(id : String) = networkDataProvider.getCoinInfo(id)

    override fun getFavorites() = localDataProvider.getFavorites()

    override suspend fun addFavorite(id : String) {
        networkDataProvider.getMarketCoinInfo(id)?.markets?.firstOrNull()?.let {
            localDataProvider.addFavorite(it.toEntity())
        }
    }

    override suspend fun deleteFavorite(id : String){
        localDataProvider.deleteFavorite(id)
    }
}