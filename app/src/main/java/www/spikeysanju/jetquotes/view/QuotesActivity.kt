package www.spikeysanju.jetquotes.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.RowScope.gravity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import www.spikeysanju.jetquotes.components.QuotesList
import www.spikeysanju.jetquotes.model.Quote
import www.spikeysanju.jetquotes.ui.JetQuotesTheme


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


    companion object {
        fun launchQuoteDetails(context: Context?, quote: String, author: String) {
            val intent = Intent(context, QuoteDetails::class.java).apply {
                putExtra("quote", quote)
                putExtra("author", author)

            }
            context?.startActivity(intent)
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

@Preview(showBackground = true)
@Composable
fun App() {
    Scaffold(topBar = {
        TopAppBar(
                title = { Text(text = "JetQuotes") },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                modifier = Modifier.gravity(Alignment.CenterVertically),
                elevation = 0.dp

        )
    }, bodyContent = {
        getQuotes()?.let { quote -> QuotesList(quote) }
    })
}




