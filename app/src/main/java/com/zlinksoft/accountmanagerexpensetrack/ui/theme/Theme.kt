package com.zlinksoft.accountmanagerexpensetrack.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.MaterialTheme

@Composable
fun AccountManagerExpenseTrackTheme(
    content: @Composable () -> Unit
) {
    val colors = if (isSystemInDarkTheme()) {
        darkColorScheme(
            primary = 0xFF2196F3.toInt(),
            onPrimary = 0xFFFFFFFF.toInt(),
            primaryContainer = 0xFF1976D2.toInt(),
            onPrimaryContainer = 0xFFEEEEEE.toInt(),
            secondary = 0xFF03DAC6.toInt(),
            onSecondary = 0xFF000000.toInt(),
            secondaryContainer = 0xFF03DAC6.toInt(),
            onSecondaryContainer = 0xFF000000.toInt(),
            background = 0xFF121212.toInt(),
            onBackground = 0xFFFFFFFF.toInt(),
            surface = 0xFF1E1E1E.toInt(),
            onSurface = 0xFFFFFFFF.toInt(),
            error = 0xFFCF6679.toInt(),
            onError = 0xFF000000.toInt()
        )
    } else {
        lightColorScheme(
            primary = 0xFF2196F3.toInt(),
            onPrimary = 0xFFFFFFFF.toInt(),
            primaryContainer = 0xFFBBDEFB.toInt(),
            onPrimaryContainer = 0xFF000000.toInt(),
            secondary = 0xFF03DAC6.toInt(),
            onSecondary = 0xFF000000.toInt(),
            secondaryContainer = 0xFFE0F2F1.toInt(),
            onSecondaryContainer = 0xFF000000.toInt(),
            background = 0xFFFFFFFF.toInt(),
            onBackground = 0xFF000000.toInt(),
            surface = 0xFFF5F5F5.toInt(),
            onSurface = 0xFF000000.toInt(),
            error = 0xFFB00020.toInt(),
            onError = 0xFFFFFFFF.toInt()
        )
    }

    MaterialTheme(
        colorScheme = colors,
        typography = typography(),
        content = content
    )
}

@Composable
fun isSystemInDarkTheme(): Boolean = false // Simplified for build