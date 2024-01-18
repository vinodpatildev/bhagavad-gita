package com.vinodpatildev.saralbhagavadgitahindi.view.main

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.vinodpatildev.saralbhagavadgitahindi.R
import com.vinodpatildev.saralbhagavadgitahindi.adapters.ChapterVersesAdapter
import com.vinodpatildev.saralbhagavadgitahindi.databinding.FragmentVersesBinding
import com.vinodpatildev.saralbhagavadgitahindi.view.verse.VerseDetailsActivity
import com.vinodpatildev.saralbhagavadgitahindi.viewmodel.VersesFragmentViewModel
import com.vinodpatildev.saralbhagavadgitahindi.viewmodel.VersesFragmentViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "VersesFragmentTag"


@AndroidEntryPoint
class VersesFragment : Fragment(R.layout.fragment_verses) {
    private var _binding: FragmentVersesBinding? = null
    private val binding: FragmentVersesBinding
        get() = checkNotNull(_binding) { "binding is not initiated." }

    private val args: VersesFragmentArgs by navArgs()

    @Inject
    lateinit var versesFragmentViewModelFactory: VersesFragmentViewModelFactory
    private val versesFragmentViewModel: VersesFragmentViewModel by viewModels {
        versesFragmentViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVersesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupVersesRecyclerView(args.fragmentArgChapter.id)
    }

    private fun setupToolbar() {
        binding.appBarLayout?.apply {
            toolbarIcon.visibility = View.VISIBLE
            toolbarTitle.text = getString(R.string.chapter_label_text) + getString(R.string.space) + args.fragmentArgChapter.chapter_number_dev + getString(R.string.colon) + getString(R.string.space) + args.fragmentArgChapter.chapter_name_hi
            toolbarIcon.setImageResource(R.drawable.ic_arrow_back)
            toolbarIcon.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }
    }

    private fun setupVersesRecyclerView(chapterId: Int) {
        binding.rcvChapterVerses.layoutManager = LinearLayoutManager(context)
        versesFragmentViewModel.chapterVersesLiveData(chapterId)
            .observe(viewLifecycleOwner) {
                binding.rcvChapterVerses.adapter = ChapterVersesAdapter(it) { verse ->
                    openVerseDetailsActivity(verse.id)
                }
            }
    }

    private fun openVerseDetailsActivity(verseId: Int) {
        val intent = Intent(context, VerseDetailsActivity::class.java)
        intent.putExtra(VerseDetailsActivity.ARG_VERSE_ID, verseId)
        startActivity(intent)
        activity?.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
