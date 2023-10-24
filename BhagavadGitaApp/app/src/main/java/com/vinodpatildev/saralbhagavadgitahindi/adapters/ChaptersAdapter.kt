package com.vinodpatildev.saralbhagavadgitahindi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vinodpatildev.saralbhagavadgitahindi.R
import com.vinodpatildev.saralbhagavadgitahindi.databinding.RcvChaptersItemBinding
import com.vinodpatildev.saralbhagavadgitahindi.model.Chapter

class ChaptersAdapter(
    private val chapterList: List<Chapter>,
    private val listener: (chapter: Chapter) -> Unit
) : RecyclerView.Adapter<ChaptersAdapter.ChapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        val binding =
            RcvChaptersItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        val chapter = chapterList[position]
        holder.bind(chapter, listener)
    }

    override fun getItemCount(): Int {
        return chapterList.size
    }

    inner class ChapterViewHolder(val binding: RcvChaptersItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(chapter: Chapter, listener: (chapter: Chapter) -> Unit) {
            binding.apply {
                tvChapterNumber.text = chapter.chapter_number_devanagari + "."
                tvChapterName.text = chapter.chapter_name
                tvChapterNameMeaning.text = chapter.chapter_meaning
                tvChapterSummary.text = chapter.chapter_summary
                tvChapterSummaryFull.text = chapter.chapter_summary
            }

            binding.root.setOnClickListener {
                listener(chapter)
            }
            binding.ivDropdown.setOnClickListener {
                if (binding.tvChapterSummary.visibility == View.VISIBLE) {
                    binding.apply {
                        ivDropdown.setImageResource(R.drawable.ic_arrow_drop_down)
                        tvChapterSummary.visibility = View.GONE
                        tvChapterSummaryFull.visibility = View.VISIBLE
                    }
                } else {
                    binding.apply {
                        ivDropdown.setImageResource(R.drawable.ic_arrow_drop_up)
                        tvChapterSummary.visibility = View.VISIBLE
                        tvChapterSummaryFull.visibility = View.GONE
                    }
                }
            }
        }
    }
}