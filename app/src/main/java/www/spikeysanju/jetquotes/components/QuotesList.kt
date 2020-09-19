package www.spikeysanju.jetquotes.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import www.spikeysanju.jetquotes.model.Quote

@Composable
fun QuotesList(quotes: List<Quote>) {
    LazyColumnFor(items = quotes) {
        Column(modifier = Modifier.padding(36.dp, 12.dp, 0.dp, 12.dp)) {
            QuotesCard(it)
        }
    }
}