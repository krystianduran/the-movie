package com.kraigdev.themovie.modules.common.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class GeneralPreferences(private val context: Context) {

    companion object {
        private const val GENERAL_PREFERENCES_DATA_STORE = "general_preferences_data_store"
        private const val GENERAL_PREFERENCE_MIGRATION = "general_preference"
        private val SHOW_ONBOARDING_KEY = booleanPreferencesKey("show_onboarding")
    }

    private val Context.generalPreferences: DataStore<Preferences> by preferencesDataStore(
        name = GENERAL_PREFERENCES_DATA_STORE,
        produceMigrations = { migrationContext ->
            listOf(
                SharedPreferencesMigration(migrationContext, GENERAL_PREFERENCE_MIGRATION)
            )
        }
    )

    suspend fun saveShowOnboarding() {
        context.generalPreferences.edit { editor ->
            editor[SHOW_ONBOARDING_KEY] = true
        }
    }

    val showOnboarding = context.generalPreferences.data.map { preferences ->
        preferences[SHOW_ONBOARDING_KEY]
    }

    suspend fun clean() {
        context.generalPreferences.edit { it.clear() }
    }
}
