package www.spikeysanju.jetquotes.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = secondaryLightColor,
    primaryVariant = secondaryDarkColor,
    secondary = secondaryDarkColor,
    surface = Color.Black,
    onBackground = Color.White,

)

private val LightColorPalette = lightColors(
    primary = primaryLightColor,
    primaryVariant = primaryDarkColor,
    secondary = secondaryDarkColor,
    surface = Color.White,
    onBackground = Color.Black

    /* Other default colors to override
background = Color.White,
surface = Color.White,
onPrimary = Color.White,
onSecondary = Color.Black,
onBackground = Color.Black,
onSurface = Color.Black,
*/
)

@Composable
fun JetQuotesTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
            colors = colors,
            typography = typography,
            shapes = shapes,
            content = content
    )
}