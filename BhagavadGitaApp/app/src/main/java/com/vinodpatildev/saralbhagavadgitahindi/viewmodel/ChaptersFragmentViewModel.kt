package com.vinodpatildev.saralbhagavadgitahindi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.vinodpatildev.saralbhagavadgitahindi.model.Chapter
import com.vinodpatildev.saralbhagavadgitahindi.repository.Repository
import com.vinodpatildev.saralbhagavadgitahindi.utils.DataStoreRepository

class ChaptersFragmentViewModel(
    private val app: Application,
    private val dataStoreRepository: DataStoreRepository,
    private val repository: Repository
) : AndroidViewModel(app) {
    val chaptersLiveData : LiveData<List<Chapter>>
        get() = repository.getAllChapters().asLiveData()
}