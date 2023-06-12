package com.vinodpatildev.saralbhagavadgitahindi.view.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.vinodpatildev.saralbhagavadgitahindi.R
import com.vinodpatildev.saralbhagavadgitahindi.adapters.ChapterVersesAdapter
import com.vinodpatildev.saralbhagavadgitahindi.databinding.FragmentChapterVersesBinding
import com.vinodpatildev.saralbhagavadgitahindi.model.Chapter
import com.vinodpatildev.saralbhagavadgitahindi.model.Verse
import com.vinodpatildev.saralbhagavadgitahindi.utils.Constants
import com.vinodpatildev.saralbhagavadgitahindi.view.verse.VerseActivity
import com.vinodpatildev.saralbhagavadgitahindi.viewmodel.ChapterVersesFragmentViewModel
import com.vinodpatildev.saralbhagavadgitahindi.viewmodel.ChapterVersesFragmentViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val ARG_CHAPTER = "FRAGMENT_ARG_CHAPTER"

@AndroidEntryPoint
class ChapterVersesFragment : Fragment(R.layout.fragment_chapter_verses) {
    private var paramChapter: Chapter? = null
    private var binding: FragmentChapterVersesBinding? = null

    @Inject
    lateinit var chapterVersesFragmentViewModelFactory: ChapterVersesFragmentViewModelFactory
    private lateinit var chapterVersesFragmentViewModel: ChapterVersesFragmentViewModel
    private var chapterVersesAdapter: ChapterVersesAdapter? = null

    private lateinit var mAdRequest: AdRequest
//    private var mInterstitialAd: InterstitialAd? = null
    private lateinit var mVerseParam : Verse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramChapter = it.getParcelable(ARG_CHAPTER)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChapterVersesBinding.bind(view)
        chapterVersesFragmentViewModel = ViewModelProvider(this, chapterVersesFragmentViewModelFactory).get( ChapterVersesFragmentViewModel::class.java )

        initializeAdRequest()

//        initializeInterstitialAd()

        initializeBannerAd()
        loadBannerAd()
    }

    private fun initializeAdRequest() {
        mAdRequest = AdRequest.Builder().build()
    }

//    private fun initializeInterstitialAd() {
//        InterstitialAd.load(requireContext(),getString(R.string.admob_interstitial_ad_unit_id), mAdRequest, object : InterstitialAdLoadCallback() {
//            override fun onAdFailedToLoad(adError: LoadAdError) {
//                mInterstitialAd = null
//            }
//
//            override fun onAdLoaded(interstitialAd: InterstitialAd) {
//                mInterstitialAd = interstitialAd
//            }
//        })
//        mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
//            override fun onAdDismissedFullScreenContent() {
//                mInterstitialAd = null
//                openVerseActivity()
//            }
//
//            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
//                mInterstitialAd = null
//            }
//        }
//    }
//    private fun loadInterstitialAd() {
//        if (mInterstitialAd != null) {
//            mInterstitialAd?.show(requireActivity())
//        }
//    }

    private fun initializeBannerAd() {
        MobileAds.initialize(requireContext())
    }

    private fun loadBannerAd() {
        binding?.adView?.loadAd(mAdRequest)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
        setupChapterVersesRecyclerView(paramChapter?.chapter_number!!)
    }

    private fun setupToolbar() {
        binding?.appBarLayout?.apply {
            toolbarIcon.visibility = View.VISIBLE
            toolbarIcon.setImageResource(R.drawable.ic_arrow_back)
            toolbarIcon.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            toolbarTitle.text = getString(R.string.text_chapter_devnagari) + " " + paramChapter?.chapter_number_devanagari + " : " + paramChapter?.chapter_name
        }
    }

    private fun setupChapterVersesRecyclerView(chapterNo: Int) {
        chapterVersesFragmentViewModel.chapterVersesLiveData(chapterNo).observe(viewLifecycleOwner){
            chapterVersesAdapter = ChapterVersesAdapter(it){
                mVerseParam = it
                openVerseActivity()
            }
            binding?.apply {
                rcvChapterVerses.layoutManager = LinearLayoutManager(requireContext())
                rcvChapterVerses.adapter = chapterVersesAdapter
            }
        }
    }

//    private fun showInterstitialAdAndOpenVerseActivity(){
//        loadInterstitialAd()
//    }

    private fun openVerseActivity() {
        val verseActivityIntent = Intent(requireContext(), VerseActivity::class.java)
        verseActivityIntent.putExtra(VerseActivity.ARG_VERSE, mVerseParam)
        startActivity(verseActivityIntent)
        activity?.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
    }

    companion object {
        @JvmStatic
        fun newInstance(chapter: Chapter) =
            ChapterVersesFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_CHAPTER, chapter)
                }
            }
    }
}