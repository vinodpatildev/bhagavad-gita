package com.vinodpatildev.saralbhagavadgitahindi.view.verse

import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import com.vinodpatildev.saralbhagavadgitahindi.R
import com.vinodpatildev.saralbhagavadgitahindi.databinding.ActivityVerseDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class VerseDetailsActivity : AppCompatActivity() {
    private var binding: ActivityVerseDetailsBinding?= null
    private var mCurrentVerseId by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerseDetailsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        mCurrentVerseId = intent.getIntExtra(ARG_VERSE_ID, 1)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right)
    }

    companion object {
        val ARG_VERSE_ID = "activity_verse_arg_curr_verse"
    }
}