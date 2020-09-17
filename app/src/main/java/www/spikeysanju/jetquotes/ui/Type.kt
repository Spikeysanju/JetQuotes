package www.spikeysanju.jetquotes.ui


import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.sp
import www.spikeysanju.jetquotes.R


private val futura = fontFamily(
        font(R.font.futurabook),
        font(R.font.futuramedium, FontWeight.W500),
        font(R.font.futurabold, FontWeight.Bold))

private val garamond = fontFamily(
        font(R.font.garamond))


private val MyTypography = Typography(


        h1 = TextStyle(
                fontFamily = garamond,
                fontWeight = FontWeight.W300,
                fontSize = 14.sp
        ),

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

val typography = Typography(defaultFontFamily = futura)



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