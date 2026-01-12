package com.vinodpatildev.saralbhagavadgitahindi.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vinodpatildev.saralbhagavadgitahindi.databinding.RcvSearchedVersesItemBinding
import com.vinodpatildev.saralbhagavadgitahindi.model.Verse
import kotlin.plus

class SearchedVersesAdapter (private val verseList : List<Verse>, private val listener : (verse : Verse) -> Unit) : RecyclerView.Adapter<SearchedVersesAdapter.SearchedVerseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedVerseViewHolder {
        val binding = RcvSearchedVersesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SearchedVerseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchedVerseViewHolder, position: Int) {
        val verse = verseList[position]
        holder.bind(verse, listener)
    }

    override fun getItemCount(): Int {
        return verseList.size
    }

    inner class SearchedVerseViewHolder(val binding : RcvSearchedVersesItemBinding) : RecyclerView.ViewHolder(binding.root){

        @SuppressLint("SetTextI18n")
        fun bind(verse : Verse, listener : (verse : Verse) -> Unit){
            binding.apply {
                val verseText = verse.verse_org_dev.substringBefore("||").replace("|","॥")
                tvVerse.text = verseText + " " + "॥" + verse.chapter_number_dev + "." + verse.verse_number_dev+ "॥"
            }

            binding.root.setOnClickListener {
                listener(verse)
            }
        }
    }
}