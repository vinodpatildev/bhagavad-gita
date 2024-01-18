package com.vinodpatildev.saralbhagavadgitahindi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinodpatildev.saralbhagavadgitahindi.model.Verse
import com.vinodpatildev.saralbhagavadgitahindi.repository.Repository
import com.vinodpatildev.saralbhagavadgitahindi.utils.Constants.Companion.VERSE_ID_LOWER_LIMIT
import com.vinodpatildev.saralbhagavadgitahindi.utils.Constants.Companion.VERSE_ID_UPPER_LIMIT
import kotlinx.coroutines.launch

private const val TAG = "VerseDetailsFragmentViewModelTag"

class VerseDetailsFragmentViewModel(
    private val repository: Repository,
    private val argVerseId : Int
) : ViewModel() {
    private val _currentVerse: MutableLiveData<Verse> = MutableLiveData()
    val currentVerse: LiveData<Verse>
        get() = _currentVerse

    init {
        getVerse(argVerseId)
    }

    private fun getVerse(verseId: Int) {
        Log.d(TAG, "getVerse: 1")
        viewModelScope.launch {
            Log.d(TAG, "getVerse: 2")
            repository.getVerse(verseId).collect { verse ->
                Log.d(TAG, "getVerse: 3")
                _currentVerse.value = verse
            }
        }
    }

    fun loadPrevVerse() {
        currentVerse.value?.id.let { currentVerseId ->
            if ((currentVerseId != null) && ((currentVerseId - 1) >= VERSE_ID_LOWER_LIMIT)) {
                getVerse(currentVerseId - 1)
            }
        }
    }

    fun loadNextVerse() {
        currentVerse.value?.id.let { currentVerseId ->
            if ((currentVerseId != null) && ((currentVerseId + 1) <= VERSE_ID_UPPER_LIMIT)) {
                getVerse(currentVerseId + 1)
            }
        }
    }

    fun receivedSelectedVerse(searchedVerseId: Int) = getVerse(searchedVerseId)
}