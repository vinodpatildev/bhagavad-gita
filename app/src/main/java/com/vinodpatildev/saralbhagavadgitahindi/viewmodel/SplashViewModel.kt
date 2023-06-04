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
import com.vinodpatildev.saralbhagavadgitahindi.utils.DataStorePrefManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel (
    private val app: Application,
    private val dataStorePrefManager: DataStorePrefManager,
    private val repository: Repository
) : AndroidViewModel(app) {
    private val SearchedVerseMutableLiveData : MutableLiveData<Verse?> = MutableLiveData()
    val SearchedVerseLiveData : LiveData<Verse?> = SearchedVerseMutableLiveData

    fun isFirstRun() = dataStorePrefManager.isFirstRun(app)
    private fun setFirstRun() = viewModelScope.launch { dataStorePrefManager.setFirstRun(false) }

    fun initiateDatabase(
        jsonChaptersDataFile: String,
        jsonVersesDataFile: String,) {
        setFirstRun()
        createChaptersTable(jsonChaptersDataFile)
        createVersesTable(jsonVersesDataFile)
    }
    private fun createChaptersTable(jsonChaptersDataFile: String) = viewModelScope.launch(Dispatchers.IO) {
        val chaptersList = Gson().fromJson(jsonChaptersDataFile, Array<Chapter>::class.java).toList()
        repository.insertAllChapters(chaptersList)
    }
    private fun createVersesTable(jsonVersesData : String) = viewModelScope.launch(Dispatchers.IO) {
        val versesList = Gson().fromJson(jsonVersesData, Array<Verse>::class.java).toList()
        repository.insertAllVerses(versesList)
    }


}