package com.vinodpatildev.saralbhagavadgitahindi.view.verse

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.vinodpatildev.saralbhagavadgitahindi.R
import com.vinodpatildev.saralbhagavadgitahindi.adapters.VersesViewPagerAdapter
import com.vinodpatildev.saralbhagavadgitahindi.databinding.ActivityVerseBinding
import com.vinodpatildev.saralbhagavadgitahindi.model.Verse
import com.vinodpatildev.saralbhagavadgitahindi.utils.SliderTransformer
import com.vinodpatildev.saralbhagavadgitahindi.viewmodel.VerseActivityViewModel
import com.vinodpatildev.saralbhagavadgitahindi.viewmodel.VerseActivityViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class VerseActivity : AppCompatActivity() {
    private lateinit var binding : ActivityVerseBinding
    @Inject
    lateinit var verseActivityViewModelFactory: VerseActivityViewModelFactory
    lateinit var verseActivityViewModel : VerseActivityViewModel
    private lateinit var mCurrentVerse : Verse
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        verseActivityViewModel = ViewModelProvider(this,verseActivityViewModelFactory).get(VerseActivityViewModel::class.java)
        mCurrentVerse = intent.getParcelableExtra(ARG_VERSE)!!

        setupToolbar()

        verseActivityViewModel.chapterVersesLiveData(mCurrentVerse.chapter_number).observe(this){
            binding.vpVerses?.apply {
                adapter = VersesViewPagerAdapter(this@VerseActivity,it)
                offscreenPageLimit = 1
                setPageTransformer(SliderTransformer(1))
                setCurrentItem(mCurrentVerse.verse_number - 1)
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                        // This method is called when the user scrolls between pages
                    }

                    override fun onPageSelected(position: Int) {
                        viewPagerVerseChanged(it[position])
                    }

                    override fun onPageScrollStateChanged(state: Int) {
                        // This method is called when the scroll state changes
                    }
                })
            }
        }
    }

    private fun viewPagerVerseChanged(verse: Verse) {
        mCurrentVerse = verse
        binding?.appBarLayout?.toolbarTitle?.text = getString(R.string.text_chapter_devnagari) + " " + mCurrentVerse?.chapter_number_devanagari + " " + getString(R.string.text_verse_devnagari) + " " + mCurrentVerse?.verse_number_devanagari
    }

    private fun setupToolbar() {
        binding?.appBarLayout?.apply {
            toolbarIcon.visibility = View.VISIBLE
            toolbarIcon.setImageResource(R.drawable.ic_arrow_back)
            toolbarIcon.setOnClickListener {
                finish()
            }
            toolbarTitle.text = getString(R.string.text_chapter_devnagari) + " " + mCurrentVerse?.chapter_number_devanagari + " " + getString(R.string.text_verse_devnagari) + " " + mCurrentVerse?.verse_number_devanagari
        }
    }

    companion object {
        val ARG_VERSE = "activity_verse_arg_curr_verse"
    }
}