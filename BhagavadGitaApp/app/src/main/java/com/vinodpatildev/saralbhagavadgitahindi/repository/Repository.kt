package com.vinodpatildev.saralbhagavadgitahindi.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vinodpatildev.saralbhagavadgitahindi.model.Chapter
import com.vinodpatildev.saralbhagavadgitahindi.model.Note
import com.vinodpatildev.saralbhagavadgitahindi.model.Verse
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface Repository {
    suspend fun insertAllChapters(chapters : List<Chapter>)
    suspend fun insertAllVerses(verses: List<Verse>)


    fun getAllChapters() : Flow<List<Chapter>>

    fun getAllVersesOfChapter(chapterId : Int) : Flow<List<Verse>>

    fun getVerse(verseId : Int) : Flow<Verse>

    fun searchVerse(query : String) : Flow<List<Verse>>

    suspend fun insertNote(note : Note)

    suspend fun updateNote(note : Note)

    suspend fun deleteNote(note : Note)

    fun getNotes() : Flow<List<Note>>

    suspend fun getNote(id : UUID) : Note
}