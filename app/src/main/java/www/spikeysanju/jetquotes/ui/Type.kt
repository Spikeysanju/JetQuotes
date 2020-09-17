package www.spikeysanju.jetquotes.ui


import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.sp
import www.spikeysanju.jetquotes.R

// Set of Material typography styles to start with
val typography = Typography(
        body1 = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
        )
        /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

private val futura = fontFamily(
        font(R.font.futurabook),
        font(R.font.futuramedium, FontWeight.W500),
        font(R.font.futurabold, FontWeight.Bold)
)

private val MyTypography = Typography(
        body1 = TextStyle(
                fontFamily = futura,
                fontWeight = FontWeight.W300,
                fontSize = 14.sp
        ),
        caption = TextStyle(
                fontFamily = futura,
                fontSize = 12.sp
        )

)

val typographyy = Typography(defaultFontFamily = futura)



//private val appFontFamily = fontFamily(
//        fonts = listOf(
//                ResourceFont(
//                        resId = R.font.futurabook,
//                        weight = FontWeight.W900,
//                        style = FontStyle.Normal
//                ),
//                ResourceFont(
//                        resId = R.font.futuramedium,
//                        weight = FontWeight.W900,
//                        style = FontStyle.Normal
//                ),
//                ResourceFont(
//                        resId = R.font.futuraheavy,
//                        weight = FontWeight.W700,
//                        style = FontStyle.Normal
//                ),
//)
//)
//private val defaultTypography = Typography()
//val appTypography = Typography(
//        h1 = defaultTypography.h1.copy(fontFamily = appFontFamily),
//        h2 = defaultTypography.h2.copy(fontFamily = appFontFamily),
//        h3 = defaultTypography.h3.copy(fontFamily = appFontFamily),
//        h4 = defaultTypography.h4.copy(fontFamily = appFontFamily),
//        h5 = defaultTypography.h5.copy(fontFamily = appFontFamily),
//        h6 = defaultTypography.h6.copy(fontFamily = appFontFamily),
//        subtitle1 = defaultTypography.subtitle1.copy(fontFamily = appFontFamily),
//        subtitle2 = defaultTypography.subtitle2.copy(fontFamily = appFontFamily),
//        body1 = defaultTypography.body1.copy(fontFamily = appFontFamily),
//        body2 = defaultTypography.body2.copy(fontFamily = appFontFamily),
//        button = defaultTypography.button.copy(fontFamily = appFontFamily),
//        caption = defaultTypography.caption.copy(fontFamily = appFontFamily),
//        overline = defaultTypography.overline.copy(fontFamily = appFontFamily )
//)