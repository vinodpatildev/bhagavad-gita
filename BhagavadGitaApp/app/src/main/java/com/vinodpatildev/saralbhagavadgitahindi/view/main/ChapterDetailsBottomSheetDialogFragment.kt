package com.vinodpatildev.saralbhagavadgitahindi.view.main

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vinodpatildev.saralbhagavadgitahindi.R
import com.vinodpatildev.saralbhagavadgitahindi.databinding.FragmentChapterDetailsBottomSheetDialogBinding

class ChapterDetailsBottomSheetDialogFragment : BottomSheetDialogFragment() {
    private var _binding : FragmentChapterDetailsBottomSheetDialogBinding? = null
    private val binding : FragmentChapterDetailsBottomSheetDialogBinding
        get() = checkNotNull(_binding){"binding is not initiated."}
    private val args : ChapterDetailsBottomSheetDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChapterDetailsBottomSheetDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.fragmentArgChapter.let {
            binding.apply {
                tvChapterNumberText.text = getString(R.string.chapter_label_text) + " " + it.chapter_number_dev
                tvChapterName.text = it.chapter_name_hi
                tvChapterNameMeaning.text = it.chapter_meaning_hi
                tvChapterNameSummary.text = it.chapter_summary_hi


                ivClose.setOnClickListener {
                    findNavController().popBackStack()
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}