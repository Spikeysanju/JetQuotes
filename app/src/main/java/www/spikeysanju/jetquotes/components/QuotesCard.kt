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
import www.spikeysanju.jetquotes.view.MainActivity.Companion.launchQuoteDetails

@Composable
fun QuotesCard(quote: Quote) {
    val context = ContextAmbient.current

    Column(modifier = Modifier.clickable(onClick = {
        launchQuoteDetails(context, quote.quote.toString(), quote.author.toString())

    }).background(MaterialTheme.colors.primaryVariant).padding(20.dp)) {

        Text(
                text = quote.quote.toString(),
                style = typography.body1,
                color = MaterialTheme.colors.onBackground
        )
        Spacer(Modifier.preferredHeight(12.dp))
        Stack(modifier = Modifier.fillMaxSize()) {
            Text(
                    modifier = Modifier.gravity(Alignment.CenterEnd).padding(12.dp),
                    text = quote.author.toString().ifBlank { " - Unknown" },
                    style = typography.caption,
                    color = MaterialTheme.colors.onBackground
            )
            Spacer(Modifier.preferredHeight(8.dp))
        }

    }
}
