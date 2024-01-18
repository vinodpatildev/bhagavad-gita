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
    suspend fun insertAllVerses(verses: List<Verse>)

    @Query("SELECT * FROM verses_table")
    suspend fun getAllVerses(): List<Verse>

    @Query("DELETE FROM verses_table")
    suspend fun deleteAllVerses()

    @Query("SELECT * FROM verses_table WHERE chapter_id=:chapterId")
    fun getAllVersesOfChapter(chapterId: Int): Flow<List<Verse>>

    @Query("SELECT * FROM verses_table WHERE id=:verseId")
    fun getVerse(verseId: Int): Flow<Verse>

    @Query("SELECT * FROM verses_table WHERE verse_org_dev LIKE '%' || :query || '%' OR verse_org_roman LIKE '%' || :query || '%';\n")
    fun searchVerses(query: String): Flow<List<Verse>>
}