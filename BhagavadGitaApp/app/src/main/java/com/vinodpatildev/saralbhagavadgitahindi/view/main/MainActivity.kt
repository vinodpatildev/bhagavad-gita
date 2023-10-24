package com.vinodpatildev.saralbhagavadgitahindi.view.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.vinodpatildev.saralbhagavadgitahindi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import hotchemi.android.rate.AppRate


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(binding!!.fragmentContainer.id, ChaptersFragment.newInstance())
                .commit()
        }
        AppRate.with(this)
            .setInstallDays(10) // default 10, 0 means install day.
            .setLaunchTimes(10) // default 10
            .setRemindInterval(5) // default 1
            .setShowLaterButton(true) // default true
            .monitor()

        // Show a dialog if meets conditions
        AppRate.showRateDialogIfMeetsConditions(this);
    }
}