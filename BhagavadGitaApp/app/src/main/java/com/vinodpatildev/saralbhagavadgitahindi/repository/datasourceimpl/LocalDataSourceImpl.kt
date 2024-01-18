package com.vinodpatildev.saralbhagavadgitahindi.repository.datasourceimpl

import com.vinodpatildev.saralbhagavadgitahindi.db.ChapterDao
import com.vinodpatildev.saralbhagavadgitahindi.db.VerseDao
import com.vinodpatildev.saralbhagavadgitahindi.model.Chapter
import com.vinodpatildev.saralbhagavadgitahindi.model.Verse
import com.vinodpatildev.saralbhagavadgitahindi.repository.datasource.LocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class LocalDataSourceImpl(
    private val chapterDao: ChapterDao,
    private val versesDao: VerseDao
) : LocalDataSource {
    override suspend fun insertAllChaptersToGitaDB(chapters: List<Chapter>) {
        CoroutineScope(Dispatchers.IO).launch {
            chapterDao.insertAllChapters(chapters)
        }
    }

    override suspend fun insertAllVersesToGitaDB(verses: List<Verse>) {
        CoroutineScope(Dispatchers.IO).launch {
            versesDao.insertAllVerses(verses)
        }
    }

    override fun getAllChaptersFromGitaDB(): Flow<List<Chapter>> {
        return chapterDao.getAllChapters()
    }

    override fun getAllVersesOfChapterFromGitaDB(chapterNo: Int): Flow<List<Verse>> {
        return versesDao.getAllVersesOfChapter(chapterNo)
    }

    override fun getVerseFromGitaDB(verseId: Int): Flow<Verse> {
        return versesDao.getVerse(verseId)
    }

    override fun searchVersesFromGitaDB(query: String): Flow<List<Verse>> {
        return versesDao.searchVerses(query)
    }
}