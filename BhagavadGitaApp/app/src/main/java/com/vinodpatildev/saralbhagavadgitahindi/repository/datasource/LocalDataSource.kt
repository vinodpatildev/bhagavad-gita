package com.vinodpatildev.saralbhagavadgitahindi.repository.datasource

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vinodpatildev.saralbhagavadgitahindi.model.Chapter
import com.vinodpatildev.saralbhagavadgitahindi.model.Note
import com.vinodpatildev.saralbhagavadgitahindi.model.Verse
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface LocalDataSource{
    suspend fun insertAllChaptersToGitaDB(chapters : List<Chapter>)
    suspend fun insertAllVersesToGitaDB(verses: List<Verse>)

    fun getAllChaptersFromGitaDB() : Flow<List<Chapter>>

    fun getAllVersesOfChapterFromGitaDB(chapterNo : Int) : Flow<List<Verse>>

    fun getVerseFromGitaDB(verseId : Int) : Flow<Verse>

    fun searchVersesFromGitaDB(query: String): Flow<List<Verse>>

    suspend fun insertNoteToGitaDB(note : Note)

    suspend fun updateNoteToGitaDB(note : Note)

    suspend fun deleteNoteFromGitaDB(note : Note)

    fun getNotesFromGitaDB() : Flow<List<Note>>

    suspend fun getNoteFromGitaDB(id : UUID) : Note
}