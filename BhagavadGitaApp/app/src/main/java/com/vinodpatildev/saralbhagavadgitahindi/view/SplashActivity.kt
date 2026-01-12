package com.vinodpatildev.saralbhagavadgitahindi.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.vinodpatildev.saralbhagavadgitahindi.R
import com.vinodpatildev.saralbhagavadgitahindi.databinding.ActivitySplashBinding
import com.vinodpatildev.saralbhagavadgitahindi.utils.Resource
import com.vinodpatildev.saralbhagavadgitahindi.view.main.MainActivity
import com.vinodpatildev.saralbhagavadgitahindi.viewmodel.SplashViewModel
import com.vinodpatildev.saralbhagavadgitahindi.viewmodel.SplashViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    @Inject
    lateinit var splashViewModelFactory: SplashViewModelFactory
    private val splashViewModel: SplashViewModel by viewModels { splashViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        splashViewModel.firstRun.observe(this) { response ->
            when (response) {
                is Resource.Loading -> {
                    startProgressbar()
                }
                is Resource.Success -> {
                    if (response.data == true) {
                        stopProgrssbar()
                        initiateDatabase()
                    } else {
                        stopProgrssbar()
                        startMainActivity()
                    }
                }
                is Resource.Error -> {
                    stopProgrssbar()
                }
                else -> {
                    stopProgrssbar()
                }
            }
        }
        splashViewModel.startApp()
    }

    private fun startProgressbar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun stopProgrssbar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun startMainActivity() {
        CoroutineScope(Dispatchers.Default).launch {
            delay(3000)
            val intent = MainActivity.newIntent(this@SplashActivity)
            startActivity(intent)
            finish()
        }
    }

    private fun initiateDatabase() {
        val jsonChaptersDataFile =
            assets.open("chapters.json").bufferedReader().use { it.readText() }
        val jsonVersesDataFile = assets.open("verses.json").bufferedReader().use { it.readText() }
        splashViewModel.initiateDatabase(
            jsonChaptersDataFile,
            jsonVersesDataFile
        )
    }
}