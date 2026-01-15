package com.vinodpatildev.saralbhagavadgitahindi.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vinodpatildev.saralbhagavadgitahindi.R
import com.vinodpatildev.saralbhagavadgitahindi.adapters.ChaptersAdapter
import com.vinodpatildev.saralbhagavadgitahindi.databinding.FragmentChaptersBinding
import com.vinodpatildev.saralbhagavadgitahindi.model.Chapter
import com.vinodpatildev.saralbhagavadgitahindi.view.SearchVerseFragment
import com.vinodpatildev.saralbhagavadgitahindi.view.verse.VerseDetailsActivity
import com.vinodpatildev.saralbhagavadgitahindi.view.verse.VerseDetailsFragmentDirections
import com.vinodpatildev.saralbhagavadgitahindi.viewmodel.ChaptersFragmentViewModel
import com.vinodpatildev.saralbhagavadgitahindi.viewmodel.ChaptersFragmentViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.core.view.get
import androidx.core.view.size

private const val TAG = "ChaptersFragmentTag"

@AndroidEntryPoint
class ChaptersFragment : Fragment(R.layout.fragment_chapters) {
    private var _binding: FragmentChaptersBinding? = null
    private val binding: FragmentChaptersBinding
        get() = checkNotNull(_binding) { "binding is not initiated." }

    @Inject
    lateinit var chaptersFragmentViewModelFactory: ChaptersFragmentViewModelFactory
    private val chaptersFragmentViewModel: ChaptersFragmentViewModel by viewModels {
        chaptersFragmentViewModelFactory
    }

    private var chaptersAdapter: ChaptersAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChaptersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setOptionsMenu()
        setupChaptersRecyclerView()
        setupFragmentResultListeners()
    }

    private fun setupToolbar() {
        binding.appBarLayout.apply {
            toolbarTitle.textAlignment = View.TEXT_ALIGNMENT_CENTER
            toolbarTitle.text = getString(R.string.app_name)
            toolbarIcon.visibility = View.VISIBLE
            toolbarIcon.setImageResource(R.drawable.ic_book)
        }
    }

    private fun setOptionsMenu() {
        binding.appBarLayout.toolbar.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.fragment_chapters, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_search_verse -> {
                        findNavController().navigate(
                            ChaptersFragmentDirections.openSearchVerseFragment()
                        )
                        true
                    }

                    else -> {
                        false
                    }
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setupFragmentResultListeners() {
        setFragmentResultListener(SearchVerseFragment.REQUEST_SEARCHED_VERSE_ID) { _, bundle ->
            val searchedVerseId = bundle.getInt(SearchVerseFragment.BUNDLE_SEARCHED_VERSE_ID)
            openVerseDetailsActivity(searchedVerseId)
        }
    }

    private fun openVerseDetailsActivity(verseId: Int) {
        val intent = Intent(context, VerseDetailsActivity::class.java)
        intent.putExtra(VerseDetailsActivity.ARG_VERSE_ID, verseId)
        startActivity(intent)
        activity?.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
    }

    private fun setupChaptersRecyclerView() {
        binding.rcvChapters.layoutManager = LinearLayoutManager(context)
        chaptersFragmentViewModel.chaptersLiveData.observe(viewLifecycleOwner) {
            chaptersAdapter = ChaptersAdapter(it, { chapter ->
                openVersesFragment(chapter)
            }, { chapter ->
                openChapterDetailsBottomSheetDialogFragment(chapter)
            })
            binding.rcvChapters.adapter = chaptersAdapter
        }
    }

    private fun openVersesFragment(chapter: Chapter) {
        findNavController().navigate(
            ChaptersFragmentDirections.openVersesFragment(chapter)
        )
    }

    private fun openChapterDetailsBottomSheetDialogFragment(chapter: Chapter) {
        findNavController().navigate(
            ChaptersFragmentDirections.showChapterDetailsBottomSheet(chapter)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}