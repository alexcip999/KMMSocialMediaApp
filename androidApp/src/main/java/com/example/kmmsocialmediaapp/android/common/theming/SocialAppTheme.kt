package com.example.kmmsocialmediaapp.android.common.theming

import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun SocialAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
){
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme
    CompositionLocalProvider(
        LocalColors provides colors,
    ) {
        MaterialTheme(
            colorScheme = colors.material,
            shapes = Shapes,
            typography = Typography,
            content = content
        )
    }
}

