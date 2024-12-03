package com.vinodpatildev.saralbhagavadgitahindi.repository.datasourceimpl

import com.vinodpatildev.saralbhagavadgitahindi.db.ChapterDao
import com.vinodpatildev.saralbhagavadgitahindi.db.NoteDao
import com.vinodpatildev.saralbhagavadgitahindi.db.VerseDao
import com.vinodpatildev.saralbhagavadgitahindi.model.Chapter
import com.vinodpatildev.saralbhagavadgitahindi.model.Note
import com.vinodpatildev.saralbhagavadgitahindi.model.Verse
import com.vinodpatildev.saralbhagavadgitahindi.repository.datasource.LocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.UUID

class LocalDataSourceImpl(
    private val chapterDao: ChapterDao,
    private val versesDao: VerseDao,
    private val noteDao: NoteDao
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

    override suspend fun insertNoteToGitaDB(note: Note) {
        return noteDao.insertNote(note)
    }

    override suspend fun updateNoteToGitaDB(note: Note) {
        return noteDao.updateNote(note)
    }

    override suspend fun deleteNoteFromGitaDB(note: Note) {
        return noteDao.deleteNote(note)
    }

    override fun getNotesFromGitaDB(): Flow<List<Note>> {
        return noteDao.getNotes()
    }

    override suspend fun getNoteFromGitaDB(id: UUID): Note {
        return noteDao.getNote(id)
    }
}