package com.vinodpatildev.saralbhagavadgitahindi.view.verse

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.vinodpatildev.saralbhagavadgitahindi.R
import com.vinodpatildev.saralbhagavadgitahindi.databinding.FragmentVerseDetailsBinding
import com.vinodpatildev.saralbhagavadgitahindi.model.Verse
import com.vinodpatildev.saralbhagavadgitahindi.utils.getChapterName
import com.vinodpatildev.saralbhagavadgitahindi.view.SearchVerseFragment
import com.vinodpatildev.saralbhagavadgitahindi.viewmodel.VerseDetailsFragmentViewModel
import com.vinodpatildev.saralbhagavadgitahindi.viewmodel.VerseDetailsFragmentViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class VerseDetailsFragment : Fragment() {
    private var _binding: FragmentVerseDetailsBinding? = null
    private val binding: FragmentVerseDetailsBinding
        get() = checkNotNull(_binding) { "binding is not initiated." }
    private var mCurrentVerse : Verse? = null

    @Inject
    lateinit var verseDetailsFragmentViewModelFactory: VerseDetailsFragmentViewModelFactory
    private val verseDetailsFragmentViewModel: VerseDetailsFragmentViewModel by viewModels {
        verseDetailsFragmentViewModelFactory.setArgVerseId(
            requireActivity().intent.getIntExtra(
                VerseDetailsActivity.ARG_VERSE_ID, 1
            )
        )
        verseDetailsFragmentViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVerseDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setVerseObserver()
        setFabButtonListeners()
        setupFragmentResultListeners()

        binding.scrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY >= oldScrollY) {
                binding.apply {
                    fabPrevVerse.hide()
                    fabNextVerse.hide()
                }
            } else {
                binding.apply {
                    fabPrevVerse.show()
                    fabNextVerse.show()
                }
            }
        }
    }

    private fun setupFragmentResultListeners() {
        setFragmentResultListener(SearchVerseFragment.REQUEST_SEARCHED_VERSE_ID) { _, bundle ->
            val searchedVerseId = bundle.getInt(SearchVerseFragment.BUNDLE_SEARCHED_VERSE_ID)
            verseDetailsFragmentViewModel.receivedSelectedVerse(searchedVerseId)
        }
    }

    private fun setFabButtonListeners() {
        binding.apply {
            fabPrevVerse.setOnClickListener {
                verseDetailsFragmentViewModel.loadPrevVerse()
            }
            fabNextVerse.setOnClickListener {
                verseDetailsFragmentViewModel.loadNextVerse()
            }
        }
    }

    private fun setVerseObserver() {
        verseDetailsFragmentViewModel.currentVerse.observe(viewLifecycleOwner) { verse: Verse? ->
            verse.let {
                mCurrentVerse = it
                updateUI(it)
            }
        }
    }

    private fun updateUI(verse: Verse?) {
        binding.apply {
            tvChapterName.text = getChapterName(requireContext(), verse?.chapter_id!!)
            tvVerseNumber.text = verse?.chapter_number_dev + "." + verse?.verse_number_dev
            tvVerse.text = verse.verse_org_dev
            tvSynonymsText.text = verse.synonyms_hi
            tvTranslationText.text = verse.translation_hi
            appBarLayout.toolbarTitle.text =
                getString(R.string.chapter_label_text) + " " + verse.chapter_number_dev + " " + getString(
                    R.string.verse_label_text
                ) + " " + verse.verse_number_dev
        }
    }
    fun highlightSynonymsText(inputText: String): SpannableString {
        val spannableString = SpannableString(inputText)
        val lines = inputText.split(';')
        var startIndex = 0
        for (line in lines) {
            var hyphenIndex = line.indexOf('-')
            if(hyphenIndex == -1) hyphenIndex = line.length

            spannableString.setSpan(
                ForegroundColorSpan(Color.BLACK),
                startIndex,
                startIndex + hyphenIndex,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            if (hyphenIndex < line.length - 1) {
                spannableString.setSpan(
                    ForegroundColorSpan(Color.GRAY),
                    startIndex + hyphenIndex + 1,
                    startIndex + line.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            startIndex += line.length + 1
        }
        return spannableString
    }

    private fun setupToolbar() {
        binding.appBarLayout?.apply {
            toolbarIcon.visibility = View.VISIBLE
            toolbarIcon.setImageResource(R.drawable.ic_arrow_back)
            toolbarIcon.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
        setOptionsMenu()
    }

    private fun setOptionsMenu() {
        binding.appBarLayout.toolbar.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.fragment_verse_details, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_search_verse -> {
                        findNavController().navigate(
                            VerseDetailsFragmentDirections.openSearchVerseFragment()
                        )
                        true
                    }
//                    R.id.action_add_note -> {
//                        findNavController().navigate(
//                            VerseDetailsFragmentDirections.showCreateNoteBottomSheet(mCurrentVerse?.id!!)
//                        )
//                        true
//                    }
//                    R.id.action_like -> {
//                        true
//                    }
                    else -> {
                        false
                    }
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}