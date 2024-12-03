package com.vinodpatildev.saralbhagavadgitahindi.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey val id : UUID,
    val verse_id : Int,
    val title : String,
    val text : String,
    val date : Date
)
