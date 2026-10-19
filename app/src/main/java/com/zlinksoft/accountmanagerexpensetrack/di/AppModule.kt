package com.zlinksoft.accountmanagerexpensetrack.di

import com.zlinksoft.accountmanagerexpensetrack.data.AccountRepository
import com.zlinksoft.accountmanagerexpensetrack.data.AppDatabase
import com.zlinksoft.accountmanagerexpensetrack.utils.PreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@dagger.hilt.android.qualifiers.ApplicationContext context: android.content.Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideAccountRepository(database: AppDatabase): AccountRepository {
        return AccountRepository(database.accountDao(), database.transactionDao())
    }

    @Provides
    @Singleton
    fun providePreferencesManager(@dagger.hilt.android.qualifiers.ApplicationContext context: android.content.Context): PreferencesManager {
        return PreferencesManager
    }
}