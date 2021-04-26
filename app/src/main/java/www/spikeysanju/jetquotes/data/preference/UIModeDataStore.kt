/*
 *
 *  *
 *  *  * MIT License
 *  *  *
 *  *  * Copyright (c) 2020 Sanju S
 *  *  *
 *  *  * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  *  * of this software and associated documentation files (the "Software"), to deal
 *  *  * in the Software without restriction, including without limitation the rights
 *  *  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  *  * copies of the Software, and to permit persons to whom the Software is
 *  *  * furnished to do so, subject to the following conditions:
 *  *  *
 *  *  * The above copyright notice and this permission notice shall be included in all
 *  *  * copies or substantial portions of the Software.
 *  *  *
 *  *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  *  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  *  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  *  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  *  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  *  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  *  * SOFTWARE.
 *  *
 *
 */

package www.spikeysanju.jetquotes.data.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

abstract class PrefsDataStore(context: Context, fileName: String) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(fileName)
    val mDataStore: DataStore<Preferences> = context.dataStore
}


class UIModeDataStore(context: Context) : PrefsDataStore(context = context, PREF_FILE_UI_MODE),
    UIModeImpl {

    // used to get the data from datastore
    override val uiMode: Flow<Boolean>
        get() = mDataStore.data.map { preferences ->
            val uiMode = preferences[UI_MODE_KEY] ?: false
            uiMode
        }


    // used to save the ui preference to datastore
    override suspend fun saveToDataStore(isNightMode: Boolean) {
        mDataStore.edit { preferences ->
            preferences[UI_MODE_KEY] = isNightMode
        }
    }


    companion object {
        private const val PREF_FILE_UI_MODE = "ui_mode_preference"
        private val UI_MODE_KEY = booleanPreferencesKey("ui_mode")
    }
}

interface UIModeImpl {
    val uiMode: Flow<Boolean>
    suspend fun saveToDataStore(isNightMode: Boolean)
}