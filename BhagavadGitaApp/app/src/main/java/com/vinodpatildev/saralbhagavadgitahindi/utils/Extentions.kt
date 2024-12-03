package com.vinodpatildev.saralbhagavadgitahindi.utils

import android.content.Context
import com.vinodpatildev.saralbhagavadgitahindi.R

fun getChapterName(context: Context, chapterNumber: Int): String {
    val chapterNamesArray = context.resources.getStringArray(R.array.chapter_names_bhagavad_gita)
    return if (chapterNumber in 1 until chapterNamesArray.size +1) {
        chapterNamesArray[chapterNumber-1]
    } else {
        "Invalid Index"
    }
}