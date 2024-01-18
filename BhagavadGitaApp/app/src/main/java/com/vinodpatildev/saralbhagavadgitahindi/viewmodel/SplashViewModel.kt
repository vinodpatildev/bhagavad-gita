package com.vinodpatildev.saralbhagavadgitahindi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.vinodpatildev.saralbhagavadgitahindi.model.Chapter
import com.vinodpatildev.saralbhagavadgitahindi.model.Verse
import com.vinodpatildev.saralbhagavadgitahindi.repository.Repository
import com.vinodpatildev.saralbhagavadgitahindi.utils.DataStoreRepository
import com.vinodpatildev.saralbhagavadgitahindi.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashViewModel(
    private val app: Application,
    private val dataStoreRepository: DataStoreRepository,
    private val repository: Repository
) : AndroidViewModel(app) {
    private val _firstRun: MutableLiveData<Resource<Boolean>> = MutableLiveData()
    val firstRun: LiveData<Resource<Boolean>> = _firstRun


    fun startApp() {
        _firstRun.value = Resource.Loading()
        viewModelScope.launch {
            try {
                dataStoreRepository.prefFirstRunFlow.collect { isFirstRun ->
                    _firstRun.value = Resource.Success(isFirstRun)
                }
            } catch (ex: Exception) {
                _firstRun.value = Resource.Error(ex.message!!)
            }
        }
    }

    fun initiateDatabase(
        jsonChaptersDataFile: String,
        jsonVersesDataFile: String,
    ) {
        _firstRun.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                createChaptersTable(jsonChaptersDataFile)
                createVersesTable(jsonVersesDataFile)
                withContext(Dispatchers.Main) {
                    dataStoreRepository.setFirstRun(false)
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    _firstRun.value = Resource.Error(ex.message!!)
                }
            }
        }
    }

    private suspend fun createChaptersTable(jsonChaptersDataFile: String) {
        val chaptersList =
            Gson().fromJson(jsonChaptersDataFile, Array<Chapter>::class.java).toList()
        repository.insertAllChapters(chaptersList)
    }

    private suspend fun createVersesTable(jsonVersesData: String) {
        val versesList = Gson().fromJson(jsonVersesData, Array<Verse>::class.java).toList()
        repository.insertAllVerses(versesList)
    }
}