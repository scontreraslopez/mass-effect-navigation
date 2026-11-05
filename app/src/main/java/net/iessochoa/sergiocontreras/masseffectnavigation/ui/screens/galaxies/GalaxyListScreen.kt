package net.iessochoa.sergiocontreras.masseffectnavigation.ui.screens.galaxies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.masseffectnavigation.data.GalaxyData
import net.iessochoa.sergiocontreras.masseffectnavigation.model.Galaxy
import net.iessochoa.sergiocontreras.masseffectnavigation.model.StarSystem
import net.iessochoa.sergiocontreras.masseffectnavigation.ui.components.GalaxyCard
import net.iessochoa.sergiocontreras.masseffectnavigation.ui.components.StarfieldBackground
import net.iessochoa.sergiocontreras.masseffectnavigation.ui.theme.MassEffectNavigationTheme

/**
 * Pantalla 1: listado de galaxias.
 * Stateless: recibe los datos y avisa hacia arriba con lambdas.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalaxyListScreen(
    galaxies: List<Galaxy>,
    systems: List<StarSystem>,
    onGalaxyClick: (Galaxy) -> Unit,
    onProgressClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    StarfieldBackground(modifier) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = {
                        Column {
                            Text("GALAXY TRACKER", style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.primary)
                            Text("Mapa galáctico", style = MaterialTheme.typography.headlineMedium)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                )
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(onClick = onProgressClick) {
                    Text("Ver progreso")
                }
            },
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier.padding(innerPadding),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 96.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(galaxies, key = { it.id }) { galaxy ->
                    val galaxySystems = systems.filter { it.galaxyId == galaxy.id }
                    GalaxyCard(
                        galaxy = galaxy,
                        explored = galaxySystems.count { it.explored },
                        total = galaxySystems.size,
                        onClick = { onGalaxyClick(galaxy) },
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun GalaxyListScreenPreview() {
    MassEffectNavigationTheme {
        GalaxyListScreen(
            galaxies = GalaxyData.galaxies,
            systems = GalaxyData.systems.mapIndexed { i, s -> s.copy(explored = i % 3 == 0) },
            onGalaxyClick = {},
            onProgressClick = {},
        )
    }
}
