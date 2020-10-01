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

package www.spikeysanju.jetquotes.components

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.dp
import www.spikeysanju.jetquotes.model.Quote
import www.spikeysanju.jetquotes.view.QuotesActivity.Companion.launchQuoteDetails

@Composable
fun QuotesCard(quote: Quote) {
    val context = ContextAmbient.current

    Column(modifier = Modifier.clickable(onClick = {
        launchQuoteDetails(context, quote.quote.toString(), quote.author.toString())

    }).background(MaterialTheme.colors.primaryVariant).padding(12.dp)) {

        Text(
            text = """ " """,
            style = typography.h4,
            color = MaterialTheme.colors.onBackground
        )

        Text(
            text = quote.quote.toString(),
            style = typography.body1,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(12.dp, 0.dp, 0.dp, 0.dp)
        )

        Spacer(Modifier.preferredHeight(12.dp))

        Stack(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.CenterEnd).padding(12.dp),
                text = quote.author.toString().ifBlank { " - Unknown" },
                style = typography.caption,
                color = MaterialTheme.colors.onBackground
            )
            Spacer(Modifier.preferredHeight(8.dp))
        }

    }
}
