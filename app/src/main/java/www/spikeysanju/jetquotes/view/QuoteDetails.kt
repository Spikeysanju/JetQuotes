package www.spikeysanju.jetquotes.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.RowScope.gravity
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import www.spikeysanju.jetquotes.R
import www.spikeysanju.jetquotes.components.DetailCard
import www.spikeysanju.jetquotes.ui.JetQuotesTheme


class QuoteDetails : AppCompatActivity() {
    private var quote: String? = null
    private var author: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // receive bundles here
        quote = intent.getStringExtra("quote") ?: "No values for quote"
        author = intent.getStringExtra("author") ?: "No values for author"

        setContent {
            JetQuotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    DetailQuoteApp(quote = quote!!, author = author!!)
                }
            }
        }
    }
}


@Composable
fun DetailQuoteApp(quote: String, author: String) {
    val context = ContextAmbient.current
    Scaffold(topBar = {
        TopAppBar(
                title = { Text(text = "JetQuotes") },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                modifier = Modifier.gravity(Alignment.CenterVertically),
                elevation = 0.dp,

                // Set back navigation
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(vectorResource(id = R.drawable.ic_back))
                    }
                },

                // Change UI Mode Button
                actions = {
                    IconButton(onClick = {
                        Toast.makeText(context, "UI Mode Changed!", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(vectorResource(id = R.drawable.ic_day))
                    }
                }

        )
    }, bodyContent = {

        // pass quote & author params to details card
        DetailCard(
                quote = quote,
                author = author
        )

    })
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetQuotesTheme {
        DetailCard(quote = "All is well", author = "Sanju")
    }
}