package com.vinodpatildev.saralbhagavadgitahindi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vinodpatildev.saralbhagavadgitahindi.repository.Repository
import com.vinodpatildev.saralbhagavadgitahindi.utils.DataStoreRepository
import javax.inject.Inject

class SearchVerseFragmentViewModelFactory @Inject constructor(
    private val repository: Repository,
    private val dataStoreRepository: DataStoreRepository
)  : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchVerseFragmentViewModel(repository, dataStoreRepository) as T
    }
}