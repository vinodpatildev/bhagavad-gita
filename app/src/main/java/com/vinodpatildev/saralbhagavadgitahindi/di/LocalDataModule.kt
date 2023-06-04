package com.vinodpatildev.saralbhagavadgitahindi.di

import com.vinodpatildev.saralbhagavadgitahindi.db.ChapterDao
import com.vinodpatildev.saralbhagavadgitahindi.db.VerseDao
import com.vinodpatildev.saralbhagavadgitahindi.repository.datasource.LocalDataSource
import com.vinodpatildev.saralbhagavadgitahindi.repository.datasourceimpl.LocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideLocalDataSource(
        chapterDao: ChapterDao,
        verseDao: VerseDao
    ): LocalDataSource {
        return LocalDataSourceImpl(
            chapterDao,
            verseDao
        )
    }
}