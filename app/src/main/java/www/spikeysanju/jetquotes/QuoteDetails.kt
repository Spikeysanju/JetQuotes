package www.spikeysanju.jetquotes

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import www.spikeysanju.jetquotes.ui.JetQuotesTheme
import www.spikeysanju.jetquotes.ui.typography

class QuoteDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val quote = intent.getStringExtra("quote") ?: "No values for quote"
        val author = intent.getStringExtra("author") ?: "No values for author"

        setContent {
            JetQuotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    DetailCard(
                            quote = quote,
                            author = author
                    )
                }
            }
        }
    }
}


@Composable
fun DetailCard(quote: String, author: String) {
    val context = ContextAmbient.current

    Stack(modifier = Modifier.fillMaxSize()) {

        Column(
                modifier = Modifier.clickable(onClick = {
                    Toast.makeText(context, "Quote copied!", Toast.LENGTH_SHORT).show()
                }).background(MaterialTheme.colors.primaryVariant).padding(40.dp).gravity(Alignment.Center).padding(12.dp),

                ) {

            Text(
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
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetQuotesTheme {
        DetailCard(quote = "All is well", author = "Spikey Sanju")
    }
}