package co.composecoins.app.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import drewcarlson.coingecko.CoinGeckoClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun providesGecko(): CoinGeckoClient {
        return CoinGeckoClient.create()
    }
}