package com.example.gramaangana.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(

    primary = GreenPrimary,

    secondary = GreenLight,

    background = Background,

    surface = CardColor
)

private val DarkColors = darkColorScheme(

    primary = GreenPrimary,

    secondary = GreenLight
)

@Composable
fun GramaAnganaTheme(

    darkTheme: Boolean =
        isSystemInDarkTheme(),

    content: @Composable () -> Unit
) {

    val colors =
        if (darkTheme)
            DarkColors
        else
            LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}