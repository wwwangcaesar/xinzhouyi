package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val CyberTaoistColorScheme = darkColorScheme(
    primary = ImperialGold,
    onPrimary = OnImperialGold,
    primaryContainer = ImperialGoldContainer,
    onPrimaryContainer = OnImperialGoldContainer,
    inversePrimary = ImperialGoldFixedDim,
    secondary = EtherealCyan,
    onSecondary = OnEtherealCyan,
    secondaryContainer = CyanContainer,
    onSecondaryContainer = OnCyanContainer,
    tertiary = AlchemicalJade,
    onTertiary = OnAlchemicalJade,
    tertiaryContainer = JadeContainer,
    onTertiaryContainer = OnJadeContainer,
    background = SurfaceVoid,
    onBackground = OnSurface,
    surface = SurfaceVoid,
    onSurface = OnSurface,
    surfaceVariant = SurfaceContainerHighest,
    onSurfaceVariant = OnSurfaceVariant,
    surfaceContainerLowest = SurfaceContainerLowest,
    surfaceContainerLow = SurfaceContainerLow,
    surfaceContainer = SurfaceContainer,
    surfaceContainerHigh = SurfaceContainerHigh,
    surfaceContainerHighest = SurfaceContainerHighest,
    surfaceBright = SurfaceBright,
    outline = OutlineColor,
    outlineVariant = OutlineVariantColor,
    error = ErrorColor,
    onError = ErrorColor,
    errorContainer = ErrorContainerColor
)

@Composable
fun TianYanTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = CyberTaoistColorScheme,
        typography = Typography,
        content = content
    )
}
