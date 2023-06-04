package com.vinodpatildev.saralbhagavadgitahindi.repository

import android.content.Context
import com.vinodpatildev.saralbhagavadgitahindi.model.Chapter
import com.vinodpatildev.saralbhagavadgitahindi.model.Verse
import com.vinodpatildev.saralbhagavadgitahindi.repository.datasource.CacheDataSource
import com.vinodpatildev.saralbhagavadgitahindi.repository.datasource.LocalDataSource
import com.vinodpatildev.saralbhagavadgitahindi.repository.datasource.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val appContext: Context,
    private val cacheDataSource: CacheDataSource,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    ) : Repository {
    override suspend fun insertAllVerses(verses: List<Verse>) {
        localDataSource.insertAllVersesToGitaDB(verses)
    }

    override suspend fun insertAllChapters(chapters: List<Chapter>) {
        localDataSource.insertAllChaptersToGitaDB(chapters)
    }

    override fun getAllChapters(): Flow<List<Chapter>> {
        return localDataSource.getAllChaptersFromGitaDB()
    }

    override fun getAllVersesOfChapter(chapterNo: Int): Flow<List<Verse>> {
        return localDataSource.getAllVersesOfChapterFromGitaDB(chapterNo)
    }
}