package com.example.smiletryone.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "on_boarding_pref")

class DataStoreRepository(context: Context) {
    private object PreferenceKey {
        val onBoardingKey = booleanPreferencesKey(name = "on_boarding_completed")
        val userToken = stringPreferencesKey(name = "user_token")
        val idUser = intPreferencesKey(name = "user_id")
    }

    private val dataStore = context.dataStore

    suspend fun saveOnboardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKey.onBoardingKey] = completed
        }
    }

    suspend fun saveUserToken(token: String) {
        dataStore.edit { preferences ->
            preferences[PreferenceKey.userToken] = token
        }
    }

    suspend fun saveUserId(id: Int) {
        dataStore.edit { preferences ->
            preferences[PreferenceKey.idUser] = id
        }
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferenceKey.onBoardingKey] ?: false
                onBoardingState
            }
    }

    fun readUserToken(): Flow<String?> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[PreferenceKey.userToken]
            }
    }

    fun readUserId(): Flow<Int?> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[PreferenceKey.idUser]
            }
    }
}