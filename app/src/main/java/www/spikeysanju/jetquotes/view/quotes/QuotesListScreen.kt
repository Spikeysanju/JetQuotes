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

package www.spikeysanju.jetquotes.view.quotes

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import www.spikeysanju.jetquotes.components.QuotesList
import www.spikeysanju.jetquotes.components.QuotesThemeSwitch
import www.spikeysanju.jetquotes.navigation.MainActions
import www.spikeysanju.jetquotes.utils.ViewState
import www.spikeysanju.jetquotes.view.viewModel.MainViewModel

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun QuotesListScreen(
    viewModel: MainViewModel,
    toggleTheme: () -> Unit,
    actions: MainActions,
) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "JetQuotes",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
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
        val context = LocalContext.current
        when (val result = viewModel.uiState.collectAsState().value) {
            is ViewState.Error -> {
                Toast.makeText(
                    context,
                    "Error ${result.exception}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            is ViewState.Success -> {
                // pass list of quotes
                QuotesList(quotes = result.quote, actions = actions)
            }
        }
    })
}