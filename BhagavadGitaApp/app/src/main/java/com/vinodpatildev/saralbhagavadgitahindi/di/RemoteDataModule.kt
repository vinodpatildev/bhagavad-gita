package com.vinodpatildev.saralbhagavadgitahindi.di

import com.vinodpatildev.saralbhagavadgitahindi.repository.datasource.RemoteDataSource
import com.vinodpatildev.saralbhagavadgitahindi.repository.datasourceimpl.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {
    @Singleton
    @Provides
    fun provideRemoteDataSource(): RemoteDataSource {
        return RemoteDataSourceImpl()
    }
}