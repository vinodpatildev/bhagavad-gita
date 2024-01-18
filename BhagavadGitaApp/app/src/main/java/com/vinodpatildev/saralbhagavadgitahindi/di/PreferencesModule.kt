package com.vinodpatildev.saralbhagavadgitahindi.di

import android.content.Context
import android.preference.PreferenceDataStore
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.vinodpatildev.saralbhagavadgitahindi.utils.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PreferencesModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context) : DataStore<Preferences> {
        return PreferenceDataStoreFactory.create { context.preferencesDataStoreFile("settings") }
    }

    @Provides
    @Singleton
    fun provideDataStoreRepository(dataStore : DataStore<Preferences>) : DataStoreRepository {
        return DataStoreRepository(dataStore)
    }
}