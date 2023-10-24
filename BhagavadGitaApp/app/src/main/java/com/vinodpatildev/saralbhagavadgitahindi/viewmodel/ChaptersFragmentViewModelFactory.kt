package com.vinodpatildev.saralbhagavadgitahindi.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vinodpatildev.saralbhagavadgitahindi.repository.Repository
import com.vinodpatildev.saralbhagavadgitahindi.utils.DataStorePrefManager

class ChaptersFragmentViewModelFactory(
    private val app : Application,
    private val dataStorePrefManager: DataStorePrefManager,
    private val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChaptersFragmentViewModel(app,dataStorePrefManager,repository) as T
    }
}