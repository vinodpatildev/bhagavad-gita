package com.vinodpatildev.saralbhagavadgitahindi.repository

import android.content.Context
import com.vinodpatildev.saralbhagavadgitahindi.model.Chapter
import com.vinodpatildev.saralbhagavadgitahindi.model.Note
import com.vinodpatildev.saralbhagavadgitahindi.model.Verse
import com.vinodpatildev.saralbhagavadgitahindi.repository.datasource.CacheDataSource
import com.vinodpatildev.saralbhagavadgitahindi.repository.datasource.LocalDataSource
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class RepositoryImpl(
    private val appContext: Context,
    private val cacheDataSource: CacheDataSource,
    private val localDataSource: LocalDataSource
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

    override fun getAllVersesOfChapter(chapterId: Int): Flow<List<Verse>> {
        return localDataSource.getAllVersesOfChapterFromGitaDB(chapterId)
    }

    override fun getVerse(verseId: Int): Flow<Verse> {
        return localDataSource.getVerseFromGitaDB(verseId)
    }

    override fun searchVerse(query: String): Flow<List<Verse>> {
        return localDataSource.searchVersesFromGitaDB(query)
    }

    override suspend fun insertNote(note: Note) {
        return localDataSource.insertNoteToGitaDB(note)
    }

    override suspend fun updateNote(note: Note) {
        return localDataSource.updateNoteToGitaDB(note)
    }

    override suspend fun deleteNote(note: Note) {
        return localDataSource.deleteNoteFromGitaDB(note)
    }

    override fun getNotes(): Flow<List<Note>> {
        return localDataSource.getNotesFromGitaDB()
    }

    override suspend fun getNote(id: UUID): Note {
        return localDataSource.getNoteFromGitaDB(id)
    }
}