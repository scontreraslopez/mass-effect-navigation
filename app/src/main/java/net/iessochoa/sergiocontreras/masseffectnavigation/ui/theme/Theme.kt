package net.iessochoa.sergiocontreras.masseffectnavigation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

// La app es siempre oscura: estamos en el espacio.
private val SpaceColorScheme = darkColorScheme(
    primary = ParagonBlue,
    onPrimary = SpaceBlack,
    secondary = EezoTeal,
    onSecondary = SpaceBlack,
    tertiary = RenegadeRed,
    background = SpaceBlack,
    onBackground = StarWhite,
    surface = PanelBlue,
    onSurface = StarWhite,
    surfaceVariant = PanelBlueHigh,
    onSurfaceVariant = MutedText,
    surfaceContainer = PanelBlue,
    surfaceContainerHigh = PanelBlueHigh,
)

@Composable
fun MassEffectNavigationTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = SpaceColorScheme,
        typography = Typography,
        content = content
    )
}
