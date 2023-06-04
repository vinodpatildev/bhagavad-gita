package com.vinodpatildev.saralbhagavadgitahindi.utils

import android.content.Context
import androidx.datastore.preferences.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStorePrefManager @Inject constructor(@ApplicationContext context: Context) {
    private val PREFERENCES_NAME = "saral_bhagavad_gita_preferences"
    data class FilterPreferences(val firstTimeRun: Boolean)
    object PreferencesKeys{
        val FIRST_RUN = preferencesKey<Boolean>("firstRun")
    }

    private val dataStore = context.createDataStore(PREFERENCES_NAME)
    val preferencesFlow = dataStore.data
        .catch { exception ->
            if(exception is IOException){
                emit(emptyPreferences())
            }else{
                throw exception
            }
        }
        .map { preferences ->
            val firstTimeRun = preferences[PreferencesKeys.FIRST_RUN] ?: true
            FilterPreferences(firstTimeRun)
        }
    suspend fun setFirstRun(isFirstRun:Boolean){
        dataStore.edit { preferences->
            preferences[PreferencesKeys.FIRST_RUN] = isFirstRun
        }
    }
    fun isFirstRun(context: Context): Boolean = runBlocking {
        val dataStore = context.createDataStore(PREFERENCES_NAME)
        val preferences = dataStore.data.first()
        preferences[PreferencesKeys.FIRST_RUN] ?: true
    }
}
