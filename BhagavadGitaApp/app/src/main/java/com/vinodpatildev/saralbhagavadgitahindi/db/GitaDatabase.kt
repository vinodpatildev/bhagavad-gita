package com.vinodpatildev.saralbhagavadgitahindi.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.vinodpatildev.saralbhagavadgitahindi.di.ApplicationScope
import com.vinodpatildev.saralbhagavadgitahindi.model.Chapter
import com.vinodpatildev.saralbhagavadgitahindi.model.Note
import com.vinodpatildev.saralbhagavadgitahindi.model.Verse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Chapter::class, Verse::class, Note::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class GitaDatabase : RoomDatabase() {
    abstract fun chapterDao() : ChapterDao
    abstract fun verseDao() : VerseDao
    abstract fun noteDao() : NoteDao
}