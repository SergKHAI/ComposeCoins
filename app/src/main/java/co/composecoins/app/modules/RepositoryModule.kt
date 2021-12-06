package co.composecoins.app.modules

import co.composecoins.data.providers.local.LocalDataProvider
import co.composecoins.data.providers.network.NetworkDataProvider
import co.composecoins.data.repositories.CoinRepositoryImpl
import co.composecoins.domain.repos.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun providesCoinRepository(
        localDataProvider: LocalDataProvider,
        networkDataProvider: NetworkDataProvider
    ): CoinRepository {
        return CoinRepositoryImpl(localDataProvider, networkDataProvider)
    }
}