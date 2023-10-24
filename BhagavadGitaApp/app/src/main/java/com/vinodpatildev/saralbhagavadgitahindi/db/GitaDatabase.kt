package com.vinodpatildev.saralbhagavadgitahindi.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.vinodpatildev.saralbhagavadgitahindi.di.ApplicationScope
import com.vinodpatildev.saralbhagavadgitahindi.model.Chapter
import com.vinodpatildev.saralbhagavadgitahindi.model.Verse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Chapter::class, Verse::class], version = 1)
abstract class GitaDatabase : RoomDatabase() {
    abstract fun chapterDao() : ChapterDao
    abstract fun verseDao() : VerseDao

    class Callback @Inject constructor(
        private val database : Provider<GitaDatabase>,
        @ApplicationScope private val applicationScope : CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val chapterDao = database.get().chapterDao()
            val verseDao = database.get().verseDao()

            applicationScope.launch {
            }
        }
    }
}