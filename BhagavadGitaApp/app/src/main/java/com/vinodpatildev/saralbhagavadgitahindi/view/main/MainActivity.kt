package com.vinodpatildev.saralbhagavadgitahindi.view.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vinodpatildev.saralbhagavadgitahindi.R
import dagger.hilt.android.AndroidEntryPoint
import hotchemi.android.rate.AppRate

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppRate.with(this)
            .setInstallDays(10) // default 10, 0 means install day.
            .setLaunchTimes(10) // default 10
            .setRemindInterval(5) // default 1
            .setShowLaterButton(true) // default true
            .monitor()
        // Show a dialog if meets conditions
        AppRate.showRateDialogIfMeetsConditions(this);
    }

    companion object {
        fun newIntent(context: Context): Intent? {
            return Intent(context, MainActivity::class.java)
        }
    }
}