package com.vinodpatildev.saralbhagavadgitahindi.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vinodpatildev.saralbhagavadgitahindi.model.Note
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note : Note)

    @Update
    suspend fun updateNote(note : Note)

    @Delete
    suspend fun deleteNote(note : Note)

    @Query("SELECT * FROM notes_table")
    fun getNotes() : Flow<List<Note>>

    @Query("SELECT * FROM notes_table WHERE id=(:id)")
    suspend fun getNote(id : UUID) : Note
}