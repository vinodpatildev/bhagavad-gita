package com.vinodpatildev.saralbhagavadgitahindi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vinodpatildev.saralbhagavadgitahindi.databinding.RcvVersesItemBinding
import com.vinodpatildev.saralbhagavadgitahindi.model.Verse

class ChapterVersesAdapter (private val verseList : List<Verse>, private val listener : (verse : Verse) -> Unit) : RecyclerView.Adapter<ChapterVersesAdapter.ChapterVerseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterVerseViewHolder {
        val binding = RcvVersesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ChapterVerseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChapterVerseViewHolder, position: Int) {
        val verse = verseList[position]
        holder.bind(verse, listener)
    }

    override fun getItemCount(): Int {
        return verseList.size
    }

    inner class ChapterVerseViewHolder(val binding : RcvVersesItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(verse : Verse, listener : (verse : Verse) -> Unit){
            binding.apply {
                // initiate views
                tvVerse.text = verse.verse_org_dev
            }

            binding.root.setOnClickListener {
                listener(verse)
            }
        }
    }
}