package www.spikeysanju.jetquotes.model

import com.squareup.moshi.Json

data class Quote(
        @Json(name = "quoteText")
        val quote: String? = null,
        @Json(name = "quoteAuthor")
        val author: String? = null
)