package co.composecoins.app.modules

import android.content.Context
import androidx.room.Room
import co.composecoins.data.providers.local.database.Database
import co.composecoins.data.providers.local.database.daos.CoinGeckoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): Database {
        return Room.databaseBuilder(
            appContext,
            Database::class.java,
            "CoinDB"
        ).build()
    }
}