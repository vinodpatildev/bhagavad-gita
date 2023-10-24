package com.vinodpatildev.saralbhagavadgitahindi.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vinodpatildev.saralbhagavadgitahindi.model.Chapter
import kotlinx.coroutines.flow.Flow

@Dao
interface ChapterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllChapters(chapters : List<Chapter>)

    @Query("SELECT * FROM chapters_table")
    fun getAllChapters(): Flow<List<Chapter>>

    @Query("DELETE FROM chapters_table")
    suspend fun deleteAllChapters()


}