package com.vinodpatildev.saralbhagavadgitahindi.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import java.io.IOException

class DataStoreRepository(private val dataStore: DataStore<Preferences>) {
    object PreferencesKeys {
        val PREF_FIRST_RUN = booleanPreferencesKey("firstRun")
        val PREF_STORED_SEARCH_QUERY = stringPreferencesKey("prefStoredSearchQuery")
        val PREF_LAST_SUPPORT_DIALOG = longPreferencesKey("lastSupportDialog")
    }

    val prefFirstRunFlow = dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.PREF_FIRST_RUN] ?: true
        }.distinctUntilChanged()

    val prefStoredSearchQueryFlow = dataStore.data
        .map {preferences ->
            preferences[PreferencesKeys.PREF_STORED_SEARCH_QUERY] ?: ""
        }.distinctUntilChanged()

    suspend fun setFirstRun(isFirstRun: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.PREF_FIRST_RUN] = isFirstRun
        }
    }

    suspend fun setStoredSearchQuery(query: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.PREF_STORED_SEARCH_QUERY] = query
        }
    }

    suspend fun setLastSupportDialog(time: Long) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.PREF_LAST_SUPPORT_DIALOG] = time
        }
    }

    suspend fun getLastSupportDialog(): Long {
        val prefs = dataStore.data.first()
        return prefs[PreferencesKeys.PREF_LAST_SUPPORT_DIALOG] ?: 0L
    }
}
