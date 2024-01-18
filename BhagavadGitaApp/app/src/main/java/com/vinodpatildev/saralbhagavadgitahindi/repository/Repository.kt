package com.vinodpatildev.saralbhagavadgitahindi.repository

import com.vinodpatildev.saralbhagavadgitahindi.model.Chapter
import com.vinodpatildev.saralbhagavadgitahindi.model.Verse
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun insertAllChapters(chapters : List<Chapter>)
    suspend fun insertAllVerses(verses: List<Verse>)


    fun getAllChapters() : Flow<List<Chapter>>

    fun getAllVersesOfChapter(chapterId : Int) : Flow<List<Verse>>

    fun getVerse(verseId : Int) : Flow<Verse>

    fun searchVerse(query : String) : Flow<List<Verse>>
}