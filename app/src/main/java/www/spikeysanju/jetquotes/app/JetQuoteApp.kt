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

package www.spikeysanju.jetquotes.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import www.spikeysanju.jetquotes.ui.JetQuotesTheme
import www.spikeysanju.jetquotes.utils.Actions
import www.spikeysanju.jetquotes.utils.Destinations.QuoteDetails
import www.spikeysanju.jetquotes.utils.Destinations.QuoteDetailsArgs
import www.spikeysanju.jetquotes.utils.Destinations.Quotes
import www.spikeysanju.jetquotes.view.App
import www.spikeysanju.jetquotes.view.DetailQuoteApp

@Composable
fun JetQuoteApp(lifecycleScope: LifecycleCoroutineScope) {
    val navController = rememberNavController()
    val actions = remember(navController) {
        Actions(navController = navController)
    }

    JetQuotesTheme {
        NavHost(navController = navController, startDestination = Quotes) {
            composable(Quotes) {
                App(lifecycleScope = (lifecycleScope), actions = actions)

            }
            composable("$QuoteDetails/{${QuoteDetailsArgs.quote}}/{${QuoteDetailsArgs.author}}") { navBackStackEntry ->
                DetailQuoteApp(
                    quote = navBackStackEntry.arguments?.getString(QuoteDetailsArgs.quote) ?: "",
                    author = navBackStackEntry.arguments?.getString(QuoteDetailsArgs.author) ?: "",
                    onBackPress = actions.navigateUp
                )
            }
        }
    }
}