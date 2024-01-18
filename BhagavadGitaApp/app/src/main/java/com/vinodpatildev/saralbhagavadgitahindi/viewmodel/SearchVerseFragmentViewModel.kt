package com.vinodpatildev.saralbhagavadgitahindi.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinodpatildev.saralbhagavadgitahindi.model.Verse
import com.vinodpatildev.saralbhagavadgitahindi.repository.Repository
import com.vinodpatildev.saralbhagavadgitahindi.utils.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val TAG = "SearchVerseFragmentViewModelTag"

class SearchVerseFragmentViewModel(
    private val repository: Repository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<SearchVerseFragmentUiState> =
        MutableStateFlow(SearchVerseFragmentUiState())
    val uiState: StateFlow<SearchVerseFragmentUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            dataStoreRepository.prefStoredSearchQueryFlow.collectLatest { storedSearchQuery ->
                try {
                    if (storedSearchQuery.isNotEmpty()) {
                        searchResult(storedSearchQuery).collectLatest { searchedVerses ->
                            _uiState.update { oldState ->
                                oldState.copy(
                                    verses = searchedVerses,
                                    query = storedSearchQuery
                                )
                            }
                        }
                    } else {
                        _uiState.value = SearchVerseFragmentUiState()
                    }
                } catch (ex: Exception) {
                    Log.e(TAG, ex.message, ex)
                }
            }
        }
    }

    fun setQuery(query: String) {
        viewModelScope.launch {
            dataStoreRepository.setStoredSearchQuery(query)
        }
    }

    private fun searchResult(query: String): Flow<List<Verse>> {
        return repository.searchVerse(query)
    }
}

data class SearchVerseFragmentUiState(
    val verses: List<Verse> = listOf(),
    val query: String = "",
)