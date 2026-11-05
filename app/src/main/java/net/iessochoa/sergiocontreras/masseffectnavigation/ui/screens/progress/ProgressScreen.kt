package net.iessochoa.sergiocontreras.masseffectnavigation.ui.screens.progress

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.masseffectnavigation.data.GalaxyData
import net.iessochoa.sergiocontreras.masseffectnavigation.model.Galaxy
import net.iessochoa.sergiocontreras.masseffectnavigation.model.StarSystem
import net.iessochoa.sergiocontreras.masseffectnavigation.ui.components.BackButton
import net.iessochoa.sergiocontreras.masseffectnavigation.ui.components.ExplorationProgress
import net.iessochoa.sergiocontreras.masseffectnavigation.ui.components.StarfieldBackground
import net.iessochoa.sergiocontreras.masseffectnavigation.ui.theme.MassEffectNavigationTheme
import net.iessochoa.sergiocontreras.masseffectnavigation.ui.theme.galaxyAccent

/**
 * Pantalla 3: progreso global de exploración y desglose por galaxia.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressScreen(
    galaxies: List<Galaxy>,
    systems: List<StarSystem>,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val explored = systems.count { it.explored }
    val fraction = if (systems.isEmpty()) 0f else explored.toFloat() / systems.size

    StarfieldBackground(modifier) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    navigationIcon = { BackButton(onClick = onBack) },
                    title = { Text("Progreso de exploración") },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                )
            },
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier.padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                item {
                    GlobalProgress(
                        fraction = fraction,
                        explored = explored,
                        total = systems.size,
                        modifier = Modifier.padding(vertical = 16.dp),
                    )
                }
                item {
                    Text(
                        "POR GALAXIA",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                items(galaxies, key = { it.id }) { galaxy ->
                    val galaxySystems = systems.filter { it.galaxyId == galaxy.id }
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.85f)
                        ),
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Text(galaxy.name, style = MaterialTheme.typography.titleMedium)
                            ExplorationProgress(
                                explored = galaxySystems.count { it.explored },
                                total = galaxySystems.size,
                                color = galaxyAccent(galaxy.id),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun GlobalProgress(
    fraction: Float,
    explored: Int,
    total: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                progress = { fraction },
                modifier = Modifier.size(180.dp),
                strokeWidth = 12.dp,
                trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                strokeCap = StrokeCap.Round,
                gapSize = 0.dp,
            )
            Text(
                text = "${(fraction * 100).toInt()}%",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.primary,
            )
        }
        Text(
            text = "$explored de $total sistemas explorados",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Preview
@Composable
private fun ProgressScreenPreview() {
    MassEffectNavigationTheme {
        ProgressScreen(
            galaxies = GalaxyData.galaxies,
            systems = GalaxyData.systems.mapIndexed { i, s -> s.copy(explored = i % 3 != 0) },
            onBack = {},
        )
    }
}
