package www.spikeysanju.jetquotes

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.RowScope.gravity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
                    DetailQuoteApp(quote = quote, author = author)
                }
            }
        }
    }
}


@Composable
fun DetailQuoteApp(quote: String, author: String) {
    Scaffold(topBar = {
        TopAppBar(
                title = { Text(text = "JetQuotes") },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                modifier = Modifier.gravity(Alignment.CenterVertically),
                elevation = 0.dp

        )
    }, bodyContent = {

        DetailCard(
                quote = quote,
                author = author
        )

    })
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
                    text = """ " """,
                    style = typography.h5,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center
            )

            Spacer(Modifier.preferredHeight(16.dp))

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
        ctaButtons()
    }

}

@Composable
fun ctaButtons() {

    Stack(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.background(MaterialTheme.colors.primaryVariant)
                .gravity(Alignment.BottomEnd)
                .padding(30.dp, 30.dp, 0.dp, 30.dp)) {

            CTAOptions(
                    icon = Icons.Outlined.Create,
                    name = "COPY")

            Spacer(modifier = Modifier.width(30.dp))


            CTAOptions(
                    icon = Icons.Default.ArrowDropDown,
                    name = "SAVE"
            )

            Spacer(modifier = Modifier.width(30.dp))

            CTAOptions(
                    icon = Icons.Outlined.Share,
                    name = "SHARE"
            )

            Spacer(modifier = Modifier.width(30.dp))


        }
    }


}


@Composable
fun CTAOptions(icon: VectorAsset, name: String?) {
    val context = ContextAmbient.current
    Column(
            modifier = Modifier.clickable(onClick = {
                Toast.makeText(context, "cta clicked!", Toast.LENGTH_SHORT).show()
            }).background(MaterialTheme.colors.primaryVariant).padding(12.dp)) {

        Icon(
                asset = icon
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = name.toString(),
                maxLines = 1,
                style = typography.overline,
                color = MaterialTheme.colors.onBackground,
                overflow = TextOverflow.Ellipsis)
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetQuotesTheme {
        DetailCard(quote = "All is well", author = "Spikey Sanju")
    }
}