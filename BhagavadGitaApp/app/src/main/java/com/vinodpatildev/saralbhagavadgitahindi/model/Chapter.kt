package com.vinodpatildev.saralbhagavadgitahindi.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "chapters_table")
data class Chapter(
    @SerializedName("chapter_mean_en") val chapter_mean_en: String,
    @SerializedName("chapter_meaning_hi") val chapter_meaning_hi: String,
    @SerializedName("chapter_name_hi") val chapter_name_hi: String,
    @SerializedName("chapter_name_en") val chapter_name_en: String,
    @SerializedName("chapter_number_dev") val chapter_number_dev: String,
    @SerializedName("chapter_number_en") val chapter_number_en: String,
    @SerializedName("chapter_summary_en") val chapter_summary_en: String,
    @SerializedName("chapter_summary_hi") val chapter_summary_hi: String,
    @SerializedName("chapter_verses_count") val chapter_verses_count: Int,
    @SerializedName("id") @PrimaryKey val id: Int
): Parcelable
