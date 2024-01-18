package com.vinodpatildev.saralbhagavadgitahindi.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vinodpatildev.saralbhagavadgitahindi.R
import com.vinodpatildev.saralbhagavadgitahindi.adapters.SearchedVersesAdapter
import com.vinodpatildev.saralbhagavadgitahindi.databinding.FragmentSearchVerseBinding
import com.vinodpatildev.saralbhagavadgitahindi.viewmodel.SearchVerseFragmentViewModel
import com.vinodpatildev.saralbhagavadgitahindi.viewmodel.SearchVerseFragmentViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "SearchVerseFragmentTag"

@AndroidEntryPoint
class SearchVerseFragment : Fragment() {
    companion object {
        const val REQUEST_SEARCHED_VERSE_ID = "RequestSearchedVerse"
        const val BUNDLE_SEARCHED_VERSE_ID = "BundleSearcheVerse"
    }

    private var _binding: FragmentSearchVerseBinding? = null
    private val binding: FragmentSearchVerseBinding
        get() = checkNotNull(_binding) { "binding is not initiated." }

    @Inject
    lateinit var searchVerseFragmentViewModelFactory: SearchVerseFragmentViewModelFactory
    private val searchVerseFragmentViewModel: SearchVerseFragmentViewModel by viewModels { searchVerseFragmentViewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchVerseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        setSearchedVersesRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        binding.searchView.apply {
            requestFocus()
            (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
        }
    }

    private fun setSearchedVersesRecyclerView() {
        binding.rcvSearchedVerses.layoutManager = LinearLayoutManager(context)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                searchVerseFragmentViewModel.uiState.collect { uiState ->
                    binding.apply {
                        Log.i(
                            TAG,
                            "setSearchedVersesRecyclerView: query:" + uiState.query + ", verses:" + uiState.verses.toString()
                        )
                        searchView.setQuery(uiState.query, false)
                        rcvSearchedVerses.adapter = SearchedVersesAdapter(uiState.verses) { verse ->
                            setFragmentResult(
                                REQUEST_SEARCHED_VERSE_ID,
                                bundleOf(BUNDLE_SEARCHED_VERSE_ID to verse.id)
                            )
                            findNavController().popBackStack()
                        }
                    }
                }
            }
        }
    }

    private fun setToolbar() {
        binding.toolbar.apply {
            setNavigationOnClickListener { findNavController().popBackStack() }
            addMenuProvider(object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.fragment_search_verse, menu)
                    binding.searchView.apply {
                        queryHint = getString(R.string.search_label)
                        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                            override fun onQueryTextSubmit(query: String?): Boolean {
                                binding.searchView.clearFocus();
                                return true
                            }

                            override fun onQueryTextChange(query: String?): Boolean {
                                if (query != null) {
                                    searchVerseFragmentViewModel.setQuery(query)
                                }
                                return true
                            }
                        })
                    }
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    return when (menuItem.itemId) {
//                        R.id.action_filters -> {
//                            // TODO : implement filters
//                            true
//                        }

                        else -> false
                    }
                }
            }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}