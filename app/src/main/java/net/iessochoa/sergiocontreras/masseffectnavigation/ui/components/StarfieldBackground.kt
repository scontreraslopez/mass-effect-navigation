package net.iessochoa.sergiocontreras.masseffectnavigation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.masseffectnavigation.ui.theme.DeepSpace
import net.iessochoa.sergiocontreras.masseffectnavigation.ui.theme.SpaceBlack
import kotlin.random.Random

private data class Star(val x: Float, val y: Float, val radius: Float, val alpha: Float)

/**
 * Fondo degradado con estrellas. Puramente decorativo.
 * Da a su contenido el color de texto del tema (onBackground), porque encima
 * no hay ninguna Surface que lo haga.
 */
@Composable
fun StarfieldBackground(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    val stars = remember {
        val random = Random(seed = 42)
        List(140) {
            Star(
                x = random.nextFloat(),
                y = random.nextFloat(),
                radius = random.nextFloat() * 1.4f + 0.3f,
                alpha = random.nextFloat() * 0.6f + 0.15f,
            )
        }
    }

    CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onBackground) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(DeepSpace, SpaceBlack)))
                .drawBehind {
                    stars.forEach { star ->
                        drawCircle(
                            color = Color.White.copy(alpha = star.alpha),
                            radius = star.radius.dp.toPx(),
                            center = Offset(star.x * size.width, star.y * size.height),
                        )
                    }
                },
            content = content,
        )
    }
}
