package com.vinodpatildev.saralbhagavadgitahindi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vinodpatildev.saralbhagavadgitahindi.databinding.RcvChaptersItemBinding
import com.vinodpatildev.saralbhagavadgitahindi.model.Chapter

class ChaptersAdapter(
    private val chapterList: List<Chapter>,
    private val rootOnClickListener: (chapter: Chapter) -> Unit,
    private val chapterDetailsOnClickListener: (chapter: Chapter) -> Unit
) : RecyclerView.Adapter<ChaptersAdapter.ChapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        val binding =
            RcvChaptersItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        val chapter = chapterList[position]
        holder.bind(chapter, rootOnClickListener, chapterDetailsOnClickListener)
    }

    override fun getItemCount(): Int {
        return chapterList.size
    }

    inner class ChapterViewHolder(val binding: RcvChaptersItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            chapter: Chapter,
            rootOnClickListener: (chapter: Chapter) -> Unit,
            chapterDetailsOnClickListener: (chapter: Chapter) -> Unit
        ) {
            binding.apply {
                tvChapterNumberText.text = chapter.chapter_number_dev
                tvChapterName.text = chapter.chapter_name_hi
                tvChapterNameMeaning.text = chapter.chapter_meaning_hi
            }

            binding.root.setOnClickListener {
                rootOnClickListener(chapter)
            }
            binding.ivDetails.setOnClickListener {
                chapterDetailsOnClickListener(chapter)
            }
        }
    }
}