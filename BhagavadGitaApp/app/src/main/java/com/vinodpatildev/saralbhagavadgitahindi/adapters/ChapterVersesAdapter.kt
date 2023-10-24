package com.vinodpatildev.saralbhagavadgitahindi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vinodpatildev.saralbhagavadgitahindi.databinding.RcvChapterVersesItemBinding
import com.vinodpatildev.saralbhagavadgitahindi.model.Verse

class ChapterVersesAdapter (private val verseList : List<Verse>, private val listener : (verse : Verse) -> Unit) : RecyclerView.Adapter<ChapterVersesAdapter.ChapterVerseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterVerseViewHolder {
        val binding = RcvChapterVersesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ChapterVerseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChapterVerseViewHolder, position: Int) {
        val verse = verseList[position]
        holder.bind(verse, listener)
    }

    override fun getItemCount(): Int {
        return verseList.size
    }

    inner class ChapterVerseViewHolder(val binding : RcvChapterVersesItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(verse : Verse, listener : (verse : Verse) -> Unit){
            binding.apply {
                // initiate views
                tvVerse.text = verse.verse
            }

            binding.root.setOnClickListener {
                listener(verse)
            }
        }
    }
}