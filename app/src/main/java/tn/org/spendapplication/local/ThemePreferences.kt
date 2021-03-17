package tn.org.spendapplication.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tn.org.spendapplication.dataStore


class ThemePreferences(val context: Context) {


    private val theme_key = booleanPreferencesKey("theme_tag")

    fun themeValue(): Flow<Boolean> = context.dataStore.data.map {
        it[theme_key] ?: false
    }

    suspend fun updateValue(isDark: Boolean) = context.dataStore.edit {

        it[theme_key] = isDark
    }

}