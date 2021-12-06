package co.composecoins.app.modules

import co.composecoins.data.providers.local.LocalDataProvider
import co.composecoins.data.providers.local.LocalDataProviderImpl
import co.composecoins.data.providers.local.database.Database
import co.composecoins.data.providers.network.NetworkDataProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import drewcarlson.coingecko.CoinGeckoClient
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ProvidersModule {
    @Provides
    @Singleton
    fun providesNetwork(impl: CoinGeckoClient): NetworkDataProvider{
       return NetworkDataProvider(impl)
    }
    @Provides
    @Singleton
    fun providesLocal(impl: Database): LocalDataProvider{
       return LocalDataProviderImpl(impl)
    }
}