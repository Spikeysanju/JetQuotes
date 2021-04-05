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

package www.spikeysanju.jetquotes.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import www.spikeysanju.jetquotes.app.JetQuoteApp
import www.spikeysanju.jetquotes.components.QuotesList
import www.spikeysanju.jetquotes.components.QuotesThemeSwitch
import www.spikeysanju.jetquotes.data.preference.PrefsManager
import www.spikeysanju.jetquotes.data.preference.UiMode
import www.spikeysanju.jetquotes.model.Quote
import www.spikeysanju.jetquotes.utils.Actions


class QuotesActivity : AppCompatActivity() {


    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetQuoteApp(lifecycleScope = lifecycleScope)
        }
    }
}


@Composable
fun getQuotes(): List<Quote>? {
    val context = LocalContext.current
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val listType = Types.newParameterizedType(List::class.java, Quote::class.java)
    val adapter: JsonAdapter<List<Quote>> = moshi.adapter(listType)
    val myJson = context.assets.open("quotes.json").bufferedReader().use { it.readText() }
    return adapter.fromJson(myJson)
}


@Composable
fun App(lifecycleScope: LifecycleCoroutineScope, actions: Actions) {

    val prefsManager = PrefsManager(context = LocalContext.current)
    lifecycleScope.launch {
        prefsManager.uiModeFlow.collect {
            when (it) {
                UiMode.DARK -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                UiMode.LIGHT -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }
    }

    val currentTheme = isSystemInDarkTheme()
    val darkMode by prefsManager.uiModeFlow.map { uiMode ->
        when (uiMode) {
            UiMode.DARK -> {
                true
            }
            UiMode.LIGHT -> {
                false
            }
        }
    }.collectAsState(initial = currentTheme)

    val toggleTheme: () -> Unit = {
        lifecycleScope.launch {
            prefsManager.setUiMode(if (darkMode) UiMode.LIGHT else UiMode.DARK)
        }
    }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "JetQuotes",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
            elevation = 0.dp,
            actions = {
                QuotesThemeSwitch(toggleTheme)
            }
        )
    }, content = {
        // pass list of quotes
        getQuotes()?.let { quote -> QuotesList(quote, actions) }
    })
}





