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

package www.spikeysanju.jetquotes.view.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import www.spikeysanju.jetquotes.data.preference.UIModeDataStore
import www.spikeysanju.jetquotes.model.Quote
import www.spikeysanju.jetquotes.utils.UIModeState
import www.spikeysanju.jetquotes.utils.ViewState

class MainViewModel(application: Application) : AndroidViewModel(application) {

    // init UIModeDataStore
    private val uiModeDataStore = UIModeDataStore(application)

    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow<ViewState>(ViewState.Loading)
    private val _uiModeState = MutableStateFlow<UIModeState>(UIModeState.Default(false))

    // UI collects from this StateFlow to get it's state update
    val uiState = _uiState.asStateFlow()
    val uiMode = _uiModeState.asStateFlow()

    // get all quotes from assets folder
    fun getAllQuotes(application: Application) = viewModelScope.launch {
        try {
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val listType = Types.newParameterizedType(List::class.java, Quote::class.java)
            val adapter: JsonAdapter<List<Quote>> = moshi.adapter(listType)
            val myJson =
                application.assets.open("quotes.json").bufferedReader().use { it.readText() }
            _uiState.value = ViewState.Success(adapter.fromJson(myJson)!!)
        } catch (e: Exception) {
            _uiState.value = ViewState.Error(exception = e)
        }
    }

    // get ui mode
    val getUIMode = uiModeDataStore.uiMode

    fun fetchUIMode() = viewModelScope.launch {
        try {
            _uiModeState.value = UIModeState.Success(uiModeDataStore.uiMode)
        } catch (e: Exception) {
            Log.i("Error occurred", "${e.printStackTrace()}")
            _uiModeState.value = UIModeState.Error(e)
        }
    }

    // save ui mode
    fun setUIMode(isNightMode: Boolean) {
        viewModelScope.launch(IO) {
            uiModeDataStore.saveToDataStore(isNightMode)
        }
    }
}