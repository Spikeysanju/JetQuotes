package www.spikeysanju.jetquotes

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.RowScope.gravity
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import www.spikeysanju.jetquotes.model.Quote
import www.spikeysanju.jetquotes.ui.JetQuotesTheme
import www.spikeysanju.jetquotes.ui.typographyy


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetQuotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    App()
                }
            }
        }
    }
}


@Composable
fun QuotesList(quotes: List<Quote>) {
    // 1
    ScrollableColumn {
        // 2
        Column(modifier = Modifier.padding(16.dp)) {
            // 3
            for (quote in quotes) {
                // 4
                QuotesCard(quote)
            }
        }
    }
}

@Composable
fun QuotesListV2(quotes: List<Quote>) {
    // 1
    LazyColumnFor(items = quotes) {
        // 2
        Column(modifier = Modifier.padding(16.dp,12.dp,12.dp,0.dp)) {
            // 3
            QuotesCard(it)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun App() {
    Scaffold(topBar = {
        TopAppBar(
                title = { Text(text = "JetQuotes") },
                backgroundColor = Color.Black, contentColor = Color.White,
                modifier = Modifier.gravity(Alignment.CenterVertically)

        )
    }, bodyContent = {
        getQuotes()?.let { quote -> QuotesListV2(quote) }
    })
}

@Composable
fun QuotesCard(quote: Quote) {
    val context = ContextAmbient.current

    Column(modifier = Modifier.clickable(onClick = {

        Toast.makeText(context, "Clicked to bookmarks!", Toast.LENGTH_SHORT).show()

    }).background(Color(243 ,247,249)).padding(20.dp,12.dp,12.dp,12.dp)) {
        Text(
                text = quote.quote.toString(),
                style = typographyy.body1,
                color = MaterialTheme.colors.onBackground
        )
        Spacer(Modifier.preferredHeight(12.dp))
        Stack(modifier = Modifier.fillMaxSize()) {
            Text(
                    modifier = Modifier.gravity(Alignment.CenterEnd).padding(16.dp),
                    text = quote.author.toString(),
                    style = typographyy.caption,
                    color = MaterialTheme.colors.onBackground
            )
            Spacer(Modifier.preferredHeight(16.dp))
        }

    }
}


@Composable
fun getQuotes(): List<Quote>? {

    val context = ContextAmbient.current
    val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    val listType = Types.newParameterizedType(List::class.java, Quote::class.java)
    val adapter: JsonAdapter<List<Quote>> = moshi.adapter(listType)

    val file = "quotes.json"
    val myJson = context.assets.open(file).bufferedReader().use { it.readText() }

    return adapter.fromJson(myJson)

}