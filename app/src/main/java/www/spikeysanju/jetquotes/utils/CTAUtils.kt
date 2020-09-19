package www.spikeysanju.jetquotes.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent


fun Context.copyToClipboard(text: CharSequence) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("label", text)
    clipboard.setPrimaryClip(clip)
}

fun Context.shareToOthers(quote: String) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_TEXT, quote)
    startActivity(Intent.createChooser(intent, "Share via"))
}

