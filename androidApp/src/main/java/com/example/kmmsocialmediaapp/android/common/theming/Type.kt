package com.example.kmmsocialmediaapp.android.common.theming

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.kmmsocialmediaapp.android.R

val Lexend = FontFamily(
    Font(R.font.lexend_medium, FontWeight.Medium),
    Font(R.font.lexend_semibold, FontWeight.SemiBold),
    Font(R.font.lexend_bold, FontWeight.Bold)
)

val Open = FontFamily(
    Font(R.font.lexend_light, FontWeight.Light),
    Font(R.font.lexend_regular, FontWeight.Normal)
)

val Typography = Typography(
    // h6 din imaginea 1 -> Înlocuit cu titleLarge în Material 3
    titleLarge = TextStyle(
        fontFamily = Lexend, // Era Lexend în imagine, păstrat
        fontWeight = FontWeight.Bold,
        fontSize = 21.sp // Era 21.sp în imagine, păstrat
    ),

    // subtitle1 din imaginea 1 -> Înlocuit cu titleMedium în Material 3
    titleMedium = TextStyle(
        fontFamily = Lexend, // Era Lexend în imagine, păstrat
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp // Era 18.sp în imagine, păstrat
    ),

    // subtitle2 din imaginea 1 -> Înlocuit cu titleSmall în Material 3
    titleSmall = TextStyle(
        fontFamily = Lexend, // Era Lexend în imagine, păstrat
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp // Era 14.sp în imagine, păstrat
    ),

    // body1 din imaginea 1 -> Înlocuit cu bodyLarge în Material 3
    bodyLarge = TextStyle(
        fontFamily = Open, // În imagine era "OpenSans", păstrat
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp // Era 16.sp în imagine, păstrat
    ),

    // body2 din imaginea 2 -> Înlocuit cu bodyMedium în Material 3
    bodyMedium = TextStyle(
        fontFamily = Open, // În imagine era "OpenSans", păstrat
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp // Era 14.sp în imagine, păstrat
    ),

    // button din imaginea 2 -> Înlocuit cu labelLarge în Material 3
    labelLarge = TextStyle(
        fontFamily = Lexend, // În imagine era "Lexend", păstrat
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp // Era 15.sp în imagine, păstrat
    ),

    // caption din imaginea 2 -> Înlocuit cu labelMedium în Material 3
    labelMedium = TextStyle(
        fontFamily = Open, // În imagine era "OpenSans", păstrat
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp // Nouă valoare adaptată pentru caption
    ),

    // overline din imaginea 2 -> Înlocuit cu labelSmall în Material 3
    labelSmall = TextStyle(
        fontFamily = Open, // În imagine era "OpenSans", păstrat
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp // Nouă valoare adaptată pentru overline
    )
)