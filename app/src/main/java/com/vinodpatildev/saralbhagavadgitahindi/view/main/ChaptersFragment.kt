package com.vinodpatildev.saralbhagavadgitahindi.view.main

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.vinodpatildev.saralbhagavadgitahindi.R
import com.vinodpatildev.saralbhagavadgitahindi.adapters.ChaptersAdapter
import com.vinodpatildev.saralbhagavadgitahindi.databinding.FragmentChaptersBinding
import com.vinodpatildev.saralbhagavadgitahindi.model.Chapter
import com.vinodpatildev.saralbhagavadgitahindi.utils.Constants
import com.vinodpatildev.saralbhagavadgitahindi.viewmodel.ChaptersFragmentViewModel
import com.vinodpatildev.saralbhagavadgitahindi.viewmodel.ChaptersFragmentViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChaptersFragment : Fragment(R.layout.fragment_chapters) {
    private val TAG = "ChaptersFragment"
    private var binding : FragmentChaptersBinding? = null
    @Inject
    lateinit var chaptersFragmentViewModelFactory: ChaptersFragmentViewModelFactory
    private lateinit var chaptersFragmentViewModel: ChaptersFragmentViewModel
    private var chaptersAdapter : ChaptersAdapter? = null

    private var mInterstitialAd: InterstitialAd? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChaptersBinding.bind(view)
        chaptersFragmentViewModel = ViewModelProvider(this,chaptersFragmentViewModelFactory).get(ChaptersFragmentViewModel::class.java)

        initializeInterstitialAd()
        loadInterstitialAd()

        initializeBannerAd()
        loadBannerAd()
    }
    private fun initializeInterstitialAd() {
        var adRequest = AdRequest.Builder().build()
        InterstitialAd.load(requireContext(),getString(R.string.admob_interstitial_ad_unit_id), adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
            }
        })
        mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                mInterstitialAd = null
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                mInterstitialAd = null
            }
        }
    }
    private fun loadInterstitialAd() {
        val handler = Handler()
        handler.postDelayed({
            if (mInterstitialAd != null) {
                mInterstitialAd?.show(requireActivity())
            }
        }, Constants.longLongTimeToShowAd)
    }


    private fun initializeBannerAd() {
        MobileAds.initialize(requireContext())
    }

    private fun loadBannerAd() {
        val adRequest = AdRequest.Builder().build()
        binding?.adView?.loadAd(adRequest)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
        setupChaptersRecyclerView()
    }

    private fun setupToolbar() {
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