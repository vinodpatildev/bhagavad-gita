package com.vinodpatildev.saralbhagavadgitahindi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.vinodpatildev.saralbhagavadgitahindi.model.Verse
import com.vinodpatildev.saralbhagavadgitahindi.repository.Repository
import com.vinodpatildev.saralbhagavadgitahindi.utils.DataStoreRepository

class VersesFragmentViewModel (
    private val app: Application,
    private val dataStoreRepository: DataStoreRepository,
    private val repository: Repository
) : AndroidViewModel(app) {

    fun chapterVersesLiveData(chapterNo : Int) = liveData<List<Verse>> {
        repository.getAllVersesOfChapter(chapterNo).collect(){
            emit(it)
        }
    }
}