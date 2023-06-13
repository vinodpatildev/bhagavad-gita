package com.vinodpatildev.saralbhagavadgitahindi.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "chapters_table")
data class Chapter(
    @SerializedName("chapter_meaning") val chapter_meaning: String?,
    @SerializedName("chapter_name") val chapter_name: String?,
    @SerializedName("chapter_number") val chapter_number: Int,
    @SerializedName("chapter_number_devanagari") val chapter_number_devanagari: String?,
    @SerializedName("chapter_number_pronounce") val chapter_number_pronounce: String?,
    @SerializedName("chapter_summary") val chapter_summary: String?,
    @SerializedName("chapter_verses_count") val chapter_verses_count: Int,
    @SerializedName("id") @PrimaryKey val id: Int
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