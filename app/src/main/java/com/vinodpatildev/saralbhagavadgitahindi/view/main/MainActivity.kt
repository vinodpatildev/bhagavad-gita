package com.vinodpatildev.saralbhagavadgitahindi.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vinodpatildev.saralbhagavadgitahindi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

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
    }
}