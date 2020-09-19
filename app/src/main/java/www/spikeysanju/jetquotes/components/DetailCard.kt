package www.spikeysanju.jetquotes.components

import android.widget.Toast
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import www.spikeysanju.jetquotes.utils.copyToClipboard

@Composable
fun DetailCard(quote: String, author: String) {
    val context = ContextAmbient.current

    Stack(modifier = Modifier.fillMaxSize()) {

        Column(
                modifier = Modifier.clickable(onClick = {
                    context.copyToClipboard(quote.plus("").plus("- $author"))
                    Toast.makeText(context, "Quote copied!", Toast.LENGTH_SHORT).show()
                }).background(MaterialTheme.colors.primaryVariant).padding(40.dp).gravity(Alignment.Center).padding(12.dp),

                ) {

            Text(
                    modifier = Modifier.gravity(Alignment.CenterHorizontally),
                    text = """ " """,
                    style = typography.h4,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center
            )

            Spacer(Modifier.preferredHeight(16.dp))

            Text(
                    modifier = Modifier.gravity(Alignment.CenterHorizontally),
                    text = quote.ifBlank { " No Quotes found" },
                    style = typography.h5,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center
            )

            Spacer(Modifier.preferredHeight(16.dp))

            Text(
                    modifier = Modifier.gravity(Alignment.CenterHorizontally),
                    text = author.ifBlank { " - Unknown" },
                    style = typography.body1,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center
            )
        }
        CTAButtons(quote, author)
    }

}
