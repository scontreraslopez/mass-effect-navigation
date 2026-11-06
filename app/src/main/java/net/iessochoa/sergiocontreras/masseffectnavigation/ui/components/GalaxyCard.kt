package net.iessochoa.sergiocontreras.masseffectnavigation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.masseffectnavigation.data.GalaxyData
import net.iessochoa.sergiocontreras.masseffectnavigation.model.Galaxy
import net.iessochoa.sergiocontreras.masseffectnavigation.ui.theme.MassEffectNavigationTheme
import net.iessochoa.sergiocontreras.masseffectnavigation.ui.theme.galaxyAccent

@Composable
fun GalaxyCard(
    galaxy: Galaxy,
    explored: Int,
    total: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val accent = galaxyAccent(galaxy.id)

    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.85f)
        ),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // "Planeta" con brillo del color de la galaxia
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .background(
                        Brush.radialGradient(listOf(accent, accent.copy(alpha = 0.25f), Color.Transparent)),
                        CircleShape,
                    )
            )
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = galaxy.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = galaxy.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                ExplorationProgress(
                    explored = explored,
                    total = total,
                    color = accent,
                    modifier = Modifier.padding(top = 6.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GalaxyCardPreview()  {
    val firstGalaxy = GalaxyData.galaxies[0]
    val firstGalaxyPlanets = GalaxyData.systems.filter { it.galaxyId == firstGalaxy.id }
    MassEffectNavigationTheme {
        GalaxyCard(
            galaxy = firstGalaxy,
            explored = firstGalaxyPlanets.size / 2,
            total = firstGalaxyPlanets.size,
            onClick = { }
        )
    }

}
