package www.spikeysanju.jetquotes.components

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun Button(icon: VectorAsset, name: String?, modifier: Modifier) {
    Column(
            modifier.background(MaterialTheme.colors.primaryVariant).padding(12.dp)) {

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