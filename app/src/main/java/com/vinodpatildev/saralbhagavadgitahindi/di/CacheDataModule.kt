package com.vinodpatildev.saralbhagavadgitahindi.di

import com.vinodpatildev.saralbhagavadgitahindi.repository.datasource.CacheDataSource
import com.vinodpatildev.saralbhagavadgitahindi.repository.datasourceimpl.CacheDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheDataModule {
    @Singleton
    @Provides
    fun provideCacheDataSource(): CacheDataSource {
        return CacheDataSourceImpl()
    }
}