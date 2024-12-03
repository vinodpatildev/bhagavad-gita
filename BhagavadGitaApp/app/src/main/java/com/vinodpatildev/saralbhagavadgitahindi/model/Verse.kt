package com.vinodpatildev.saralbhagavadgitahindi.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "verses_table")
data class Verse(
    @SerializedName("chapter_id") val chapter_id: Int,
    @SerializedName("chapter_number_dev") val chapter_number_dev: String,
    @SerializedName("chapter_number_en") val chapter_number_en: String,
    @SerializedName("id") @PrimaryKey val id: Int,
    @SerializedName("purport_en") val purport_en: String,
    @SerializedName("purport_gu") val purport_gu: String,
    @SerializedName("purport_hi") val purport_hi: String,
    @SerializedName("purport_or") val purport_or: String,
    @SerializedName("purport_ta") val purport_ta: String,
    @SerializedName("purport_te") val purport_te: String,
    @SerializedName("synonyms_en") val synonyms_en: String,
    @SerializedName("synonyms_gu") val synonyms_gu: String,
    @SerializedName("synonyms_hi") val synonyms_hi: String,
    @SerializedName("synonyms_or") val synonyms_or: String,
    @SerializedName("synonyms_ta") val synonyms_ta: String,
    @SerializedName("synonyms_te") val synonyms_te: String,
    @SerializedName("translation_en") val translation_en: String,
    @SerializedName("translation_gu") val translation_gu: String,
    @SerializedName("translation_hi") val translation_hi: String,
    @SerializedName("translation_or") val translation_or: String,
    @SerializedName("translation_ta") val translation_ta: String,
    @SerializedName("translation_te") val translation_te: String,
    @SerializedName("verse_en") val verse_en: String,
    @SerializedName("verse_gu") val verse_gu: String,
    @SerializedName("verse_hi") val verse_hi: String,
    @SerializedName("verse_number_dev") val verse_number_dev: String,
    @SerializedName("verse_number_en") val verse_number_en: String,
    @SerializedName("verse_or") val verse_or: String,
    @SerializedName("verse_org_dev") val verse_org_dev: String,
    @SerializedName("verse_org_roman") val verse_org_roman: String,
    @SerializedName("verse_ta") val verse_ta: String,
    @SerializedName("verse_te") val verse_te: String,
    @SerializedName("like") val like: Boolean
)
