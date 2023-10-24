package com.vinodpatildev.saralbhagavadgitahindi.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName ="verses_table")
data class Verse(
    @SerializedName("chapter_number") val chapter_number: Int,
    @SerializedName("chapter_number_devanagari") val chapter_number_devanagari: String?,
    @SerializedName("id") @PrimaryKey val id: Int,
    @SerializedName("meaning") val meaning: String?,
    @SerializedName("verse") val verse: String?,
    @SerializedName("verse_number") val verse_number: Int,
    @SerializedName("verse_number_devanagari") val verse_number_devanagari: String?,
    @SerializedName("word_meanings") val word_meanings: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(chapter_number)
        parcel.writeString(chapter_number_devanagari)
        parcel.writeInt(id)
        parcel.writeString(meaning)
        parcel.writeString(verse)
        parcel.writeInt(verse_number)
        parcel.writeString(verse_number_devanagari)
        parcel.writeString(word_meanings)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Verse> {
        override fun createFromParcel(parcel: Parcel): Verse {
            return Verse(parcel)
        }

        override fun newArray(size: Int): Array<Verse?> {
            return arrayOfNulls(size)
        }
    }
}