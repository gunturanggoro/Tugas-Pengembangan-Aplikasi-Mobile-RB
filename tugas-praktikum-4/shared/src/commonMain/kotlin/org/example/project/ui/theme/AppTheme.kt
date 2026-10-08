package org.example.project.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFFB26A00),
    onPrimary = Color.White,
    background = Color(0xFFF2F4F8),
    surface = Color.White,
    surfaceContainerHighest = Color.White
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF9FA8DA),
    onPrimary = Color(0xFF1A1A1C),
    background = Color(0xFF121214),
    surface = Color(0xFF2B2B30),
    surfaceContainerHighest = Color(0xFF2B2B30)
)

@Composable
private fun animatedColor(target: Color): Color =
    animateColorAsState(
        targetValue = target,
        animationSpec = tween(durationMillis = 500),
        label = "themeColor"
    ).value

@Composable
fun AppTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val target = if (darkTheme) DarkColors else LightColors

    val colorScheme = target.copy(
        primary = animatedColor(target.primary),
        onPrimary = animatedColor(target.onPrimary),
        background = animatedColor(target.background),
        onBackground = animatedColor(target.onBackground),
        surface = animatedColor(target.surface),
        surfaceContainerHighest = animatedColor(target.surfaceContainerHighest),
        onSurface = animatedColor(target.onSurface),
        onSurfaceVariant = animatedColor(target.onSurfaceVariant),
        outline = animatedColor(target.outline)
    )

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}