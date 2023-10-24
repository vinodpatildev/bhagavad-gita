package com.vinodpatildev.saralbhagavadgitahindi.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vinodpatildev.saralbhagavadgitahindi.R
import com.vinodpatildev.saralbhagavadgitahindi.db.VerseDao
import com.vinodpatildev.saralbhagavadgitahindi.view.main.MainActivity
import com.vinodpatildev.saralbhagavadgitahindi.viewmodel.SplashViewModel
import com.vinodpatildev.saralbhagavadgitahindi.viewmodel.SplashViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    @Inject
    lateinit var versesDao: VerseDao
    @Inject
    lateinit var splashViewModelFactory: SplashViewModelFactory
    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splashViewModel = ViewModelProvider(this,splashViewModelFactory).get(SplashViewModel::class.java)
        initiateDatabase()
        startHomeActivity()
    }

    private fun startHomeActivity() {
        CoroutineScope(Dispatchers.Default).launch {
            delay(5000)
            withContext(Dispatchers.Main){
                val mainActivityIntent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(mainActivityIntent)
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
                finish()
            }
        }
    }

    private fun initiateDatabase() {
        if(splashViewModel.isFirstRun()){
            val jsonChaptersDataFile = assets.open("chapters.json").bufferedReader().use { it.readText() }
            val jsonVersesDataFile = assets.open("verses.json").bufferedReader().use { it.readText() }
            splashViewModel.initiateDatabase(
                jsonChaptersDataFile,
                jsonVersesDataFile
            )
        }
    }
}