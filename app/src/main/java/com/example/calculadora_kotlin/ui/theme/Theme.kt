package com.example.calculadora_kotlin.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF2E7D32),
    onPrimary = Color.White,
    secondary = Color(0xFF66BB6A),
    tertiary = Color(0xFF81C784),
    background = Color(0xFFF1F8E9),
    surface = Color(0xFFE8F5E9),
    onBackground = Color(0xFF212121),
    onSurface = Color(0xFF424242)
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF66BB6A),
    onPrimary = Color.Black,
    secondary = Color(0xFF2E7D32),
    tertiary = Color(0xFF1B5E20),
    background = Color(0xFF1B1B1B),
    surface = Color(0xFF2D2D2D),
    onBackground = Color(0xFFE0E0E0),
    onSurface = Color(0xFFBDBDBD)
)

@Composable
fun BasicCalcTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}