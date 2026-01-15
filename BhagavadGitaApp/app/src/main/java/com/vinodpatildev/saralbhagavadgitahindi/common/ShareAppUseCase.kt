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
        val packageName = app.packageName
        val link = "https://play.google.com/store/apps/details?id=com.vinodpatildev.saralbhagavadgitahindi"
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
                    "Begin your spiritual journey 🙏\n\n" +
                "putExtra(\n" +
                    "    Intent.EXTRA_TEXT,\n" +
                    "    \"Discover the wisdom of the Bhagavad Gita in simple Hindi \uD83D\uDCD6✨\\n\\n\" +\n" +
                    "    \"\uD83D\uDCF1 Download the app:\\n\" +\n" +
                    "    \"https://play.google.com/store/apps/details?id=com.vinodpatildev.saralbhagavadgitahindi\\n\\n\" +\n" +
                    "    \"✔ Easy to understand Hindi\\n\" +\n" +
                    "    \"✔ Clean, distraction-free reading\\n\" +\n" +
                    "    \"✔ Learn & reflect on Krishna’s teachings\\n\\n\" +\n" +
                    "    \"Begin your spiritual journey \uD83D\uDE4F\"\n" +
                    "$link"
            )
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) // 🔑 important
        }
        app.startActivity(Intent.createChooser(intent, "Share via").apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) // 🔑 chooser also needs it
        })
    }
}