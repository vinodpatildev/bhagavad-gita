package com.vinodpatildev.saralbhagavadgitahindi.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chapters_table")
data class Chapter(
    val chapter_meaning: String?,
    val chapter_name: String?,
    val chapter_number: Int,
    val chapter_number_devanagari: String?,
    val chapter_number_pronounce: String?,
    val chapter_summary: String?,
    val chapter_verses_count: Int,
    @PrimaryKey val id: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(chapter_meaning)
        parcel.writeString(chapter_name)
        parcel.writeInt(chapter_number)
        parcel.writeString(chapter_number_devanagari)
        parcel.writeString(chapter_number_pronounce)
        parcel.writeString(chapter_summary)
        parcel.writeInt(chapter_verses_count)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Chapter> {
        override fun createFromParcel(parcel: Parcel): Chapter {
            return Chapter(parcel)
        }

        override fun newArray(size: Int): Array<Chapter?> {
            return arrayOfNulls(size)
        }
    }
}