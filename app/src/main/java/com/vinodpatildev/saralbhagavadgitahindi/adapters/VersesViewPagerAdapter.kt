package com.vinodpatildev.saralbhagavadgitahindi.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vinodpatildev.saralbhagavadgitahindi.R
import com.vinodpatildev.saralbhagavadgitahindi.databinding.VpVerseLayoutItemBinding
import com.vinodpatildev.saralbhagavadgitahindi.model.Verse

class VersesViewPagerAdapter(private val context: Context, private val versesList: List<Verse>) :
    RecyclerView.Adapter<VersesViewPagerAdapter.VersesViewPagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VersesViewPagerViewHolder {
        val binding =
            VpVerseLayoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VersesViewPagerViewHolder(context, binding)
    }

    override fun onBindViewHolder(holder: VersesViewPagerViewHolder, position: Int) {
        val verse = versesList[position]
        holder.bind(verse)
    }

    override fun getItemCount(): Int {
        return versesList.size
    }

    inner class VersesViewPagerViewHolder(
        private val context: Context,
        val binding: VpVerseLayoutItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(verse: Verse) {
            binding.apply {
                verse.let {
                    val chapterNamesDevnagariArray =
                        context.resources.getStringArray(R.array.chapter_names_bhagavad_gita)

                    tvChapterName.text = chapterNamesDevnagariArray[it.chapter_number-1]
                    tvVerseNumber.text =
                        it.chapter_number_devanagari + "." + it.verse_number_devanagari
                    tvVerse.text = it.verse
                    tvSynonyms.text = it.word_meanings
                    tvTranslation.text = it.meaning
                }
            }
        }
    }
}