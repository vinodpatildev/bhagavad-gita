package com.vinodpatildev.saralbhagavadgitahindi.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.vinodpatildev.saralbhagavadgitahindi.db.ChapterDao
import com.vinodpatildev.saralbhagavadgitahindi.db.GitaDatabase
import com.vinodpatildev.saralbhagavadgitahindi.db.NoteDao
import com.vinodpatildev.saralbhagavadgitahindi.db.TypeConverter
import com.vinodpatildev.saralbhagavadgitahindi.db.VerseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideGitaDatabase(app: Application): GitaDatabase {
        return Room.databaseBuilder(app, GitaDatabase::class.java, "gita-database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideChapterDao(db: GitaDatabase): ChapterDao {
        return db.chapterDao()
    }

    @Singleton
    @Provides
    fun provideVerseDao(db: GitaDatabase): VerseDao {
        return db.verseDao()
    }

    @Singleton
    @Provides
    fun provideNoteDao(db: GitaDatabase): NoteDao {
        return db.noteDao()
    }

}
