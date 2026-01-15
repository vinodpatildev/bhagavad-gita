package com.vinodpatildev.saralbhagavadgitahindi.common

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import javax.inject.Inject
import androidx.core.net.toUri
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Singleton
class RateReviewAppUseCase @Inject constructor(
    @ApplicationContext private val app: Context,
) {
    operator fun invoke() {
        try {
            val intent = Intent(Intent.ACTION_VIEW,
                "market://details?id=com.vinodpatildev.saralbhagavadgitahindi".toUri())
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            app.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            val intent = Intent(Intent.ACTION_VIEW,
                "https://play.google.com/store/apps/details?id=com.vinodpatildev.saralbhagavadgitahindi".toUri())
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            app.startActivity(intent)
        }
    }
}