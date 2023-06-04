package com.vinodpatildev.saralbhagavadgitahindi.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vinodpatildev.saralbhagavadgitahindi.R
import com.vinodpatildev.saralbhagavadgitahindi.adapters.ChaptersAdapter
import com.vinodpatildev.saralbhagavadgitahindi.databinding.FragmentChaptersBinding
import com.vinodpatildev.saralbhagavadgitahindi.model.Chapter
import com.vinodpatildev.saralbhagavadgitahindi.viewmodel.ChaptersFragmentViewModel
import com.vinodpatildev.saralbhagavadgitahindi.viewmodel.ChaptersFragmentViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChaptersFragment : Fragment(R.layout.fragment_chapters) {
    private var binding : FragmentChaptersBinding? = null
    @Inject
    lateinit var chaptersFragmentViewModelFactory: ChaptersFragmentViewModelFactory
    private lateinit var chaptersFragmentViewModel: ChaptersFragmentViewModel
    private var chaptersAdapter : ChaptersAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChaptersBinding.bind(view)
        chaptersFragmentViewModel = ViewModelProvider(this,chaptersFragmentViewModelFactory).get(ChaptersFragmentViewModel::class.java)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
        setupChaptersRecyclerView()
    }

    private fun setupToolbar() {
//        binding?.toolbar?.apply {
//            val customTitle = LayoutInflater.from(context).inflate(R.layout.custom_toolbar_title, null)
//            (customTitle as TextView).setText(getString(R.string.app_name))
//            addView(customTitle)
//        }

        binding?.appBarLayout?.apply {
            toolbarTitle?.text = getString(R.string.splash_screen_title)
        }
    }

    private fun setupChaptersRecyclerView() {
        chaptersFragmentViewModel.chaptersLiveData.observe(viewLifecycleOwner){
            chaptersAdapter = ChaptersAdapter(it){
                openChapterVersesFragment(it)
            }
            binding?.apply {
                rcvChapters.layoutManager = LinearLayoutManager(requireContext())
                rcvChapters.adapter = chaptersAdapter
            }
        }
    }

    private fun openChapterVersesFragment(it: Chapter) {
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.fragment_container, ChapterVersesFragment.newInstance(it))
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ChaptersFragment()
    }
}