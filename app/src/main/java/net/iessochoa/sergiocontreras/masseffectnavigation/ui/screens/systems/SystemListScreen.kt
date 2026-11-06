package net.iessochoa.sergiocontreras.masseffectnavigation.ui.screens.systems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.masseffectnavigation.data.GalaxyData
import net.iessochoa.sergiocontreras.masseffectnavigation.model.Galaxy
import net.iessochoa.sergiocontreras.masseffectnavigation.model.StarSystem
import net.iessochoa.sergiocontreras.masseffectnavigation.ui.components.ExplorationProgress
import net.iessochoa.sergiocontreras.masseffectnavigation.ui.components.StarSystemItem
import net.iessochoa.sergiocontreras.masseffectnavigation.ui.components.StarfieldBackground
import net.iessochoa.sergiocontreras.masseffectnavigation.ui.theme.MassEffectNavigationTheme
import net.iessochoa.sergiocontreras.masseffectnavigation.ui.theme.galaxyAccent

/**
 * Pantalla 2: sistemas de una galaxia. Se marcan/desmarcan como explorados.
 * [systems] ya llega filtrado con los de [galaxy].
 */
@Composable
fun SystemListScreen(
    galaxy: Galaxy,
    systems: List<StarSystem>,
    onToggleExplored: (StarSystem) -> Unit,
    modifier: Modifier = Modifier,
) {
    val accent = galaxyAccent(galaxy.id)

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        item {
            Column(
                modifier = Modifier.padding(bottom = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(
                    text = galaxy.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                ExplorationProgress(
                    explored = systems.count { it.explored },
                    total = systems.size,
                    color = accent,
                )
            }
        }
        items(systems, key = { it.id }) { system ->
            StarSystemItem(
                system = system,
                accent = accent,
                onToggleExplored = { onToggleExplored(system) },
            )
        }
    }
}

@Preview
@Composable
private fun SystemListScreenPreview() {
    val galaxy = GalaxyData.galaxies[1]
    MassEffectNavigationTheme {
        StarfieldBackground {
            SystemListScreen(
                galaxy = galaxy,
                systems = GalaxyData.systems
                    .filter { it.galaxyId == galaxy.id }
                    .mapIndexed { i, s -> s.copy(explored = i % 2 == 0) },
                onToggleExplored = {},
            )
        }
    }
}
