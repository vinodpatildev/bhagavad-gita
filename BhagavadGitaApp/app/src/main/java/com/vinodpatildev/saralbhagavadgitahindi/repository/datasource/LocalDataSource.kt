package com.vinodpatildev.saralbhagavadgitahindi.repository.datasource

import com.vinodpatildev.saralbhagavadgitahindi.model.Chapter
import com.vinodpatildev.saralbhagavadgitahindi.model.Verse
import kotlinx.coroutines.flow.Flow

interface LocalDataSource{
    suspend fun insertAllChaptersToGitaDB(chapters : List<Chapter>)
    suspend fun insertAllVersesToGitaDB(verses: List<Verse>)

    fun getAllChaptersFromGitaDB() : Flow<List<Chapter>>

    fun getAllVersesOfChapterFromGitaDB(chapterNo : Int) : Flow<List<Verse>>
}