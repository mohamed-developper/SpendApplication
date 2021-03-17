package tn.org.spendapplication

import android.content.Context
import android.widget.EditText
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore


// Kotlin Extensions
fun EditText.textString(): String {
    return this.text.toString()
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "theme")


fun sum(a: Int, b: Int): Int {
    return a + b
}