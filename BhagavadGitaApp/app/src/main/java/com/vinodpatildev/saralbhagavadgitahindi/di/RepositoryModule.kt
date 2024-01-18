package com.vinodpatildev.saralbhagavadgitahindi.di

import android.content.Context
import com.vinodpatildev.saralbhagavadgitahindi.repository.Repository
import com.vinodpatildev.saralbhagavadgitahindi.repository.RepositoryImpl
import com.vinodpatildev.saralbhagavadgitahindi.repository.datasource.CacheDataSource
import com.vinodpatildev.saralbhagavadgitahindi.repository.datasource.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideNewsRepository(@ApplicationContext appContext: Context, cacheDataSource: CacheDataSource, LocalDataSource: LocalDataSource): Repository {
        return RepositoryImpl(appContext,cacheDataSource, LocalDataSource)
    }
}