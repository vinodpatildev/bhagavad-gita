package com.vinodpatildev.saralbhagavadgitahindi.common

import android.content.Context
import android.content.Intent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShareAppUseCase @Inject constructor(
    @ApplicationContext private val app: Context
) {
    operator fun invoke() {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Check out this app")
            putExtra(
                    Intent.EXTRA_TEXT,
            "Discover the wisdom of the Bhagavad Gita in simple Hindi 📖✨\n\n" +
                    "📱 Download the app:\n" +
                    "https://play.google.com/store/apps/details?id=com.vinodpatildev.saralbhagavadgitahindi\n\n" +
                    "✔ Easy to understand Hindi\n" +
                    "✔ Clean, distraction-free reading\n" +
                    "✔ Learn & reflect on Krishna’s teachings\n\n" +
                    "Begin your spiritual journey 🙏\n\n"
            )
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        app.startActivity(Intent.createChooser(intent, "Share via")
            .apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)  })
    }
}