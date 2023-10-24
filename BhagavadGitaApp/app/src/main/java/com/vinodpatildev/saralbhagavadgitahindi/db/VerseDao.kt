package com.vinodpatildev.saralbhagavadgitahindi.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vinodpatildev.saralbhagavadgitahindi.model.Verse
import kotlinx.coroutines.flow.Flow

@Dao
interface VerseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllVerses(verses : List<Verse>)

    @Query("SELECT * FROM verses_table")
    suspend fun getAllVerses(): List<Verse>

    @Query("DELETE FROM verses_table")
    suspend fun deleteAllVerses()

    @Query("SELECT * FROM verses_table WHERE chapter_number=:chapterNo")
    fun getAllVersesOfChapter(chapterNo : Int): Flow<List<Verse>>
}