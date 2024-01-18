package com.vinodpatildev.saralbhagavadgitahindi.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vinodpatildev.saralbhagavadgitahindi.repository.Repository
import javax.inject.Inject


class VerseDetailsFragmentViewModelFactory @Inject constructor(
    private val repository: Repository
) : ViewModelProvider.Factory {
    private var argVerseId = 1
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VerseDetailsFragmentViewModel(repository, argVerseId) as T
    }

    fun setArgVerseId(argVerseId: Int){
        this.argVerseId = argVerseId
    }
}