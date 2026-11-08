package net.iessochoa.sergiocontreras.masseffectnavigation

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import net.iessochoa.sergiocontreras.masseffectnavigation.data.GalaxyData
import net.iessochoa.sergiocontreras.masseffectnavigation.navigation.GalaxyList
import net.iessochoa.sergiocontreras.masseffectnavigation.navigation.Progress
import net.iessochoa.sergiocontreras.masseffectnavigation.navigation.SystemList
import net.iessochoa.sergiocontreras.masseffectnavigation.ui.GalaxyViewModel
import net.iessochoa.sergiocontreras.masseffectnavigation.ui.components.BackButton
import net.iessochoa.sergiocontreras.masseffectnavigation.ui.components.StarfieldBackground
import net.iessochoa.sergiocontreras.masseffectnavigation.ui.screens.galaxies.GalaxyListScreen
import net.iessochoa.sergiocontreras.masseffectnavigation.ui.screens.progress.ProgressScreen
import net.iessochoa.sergiocontreras.masseffectnavigation.ui.screens.systems.SystemListScreen

/**
 * Project: Mass Effect Navigation
 * From: net.iessochoa.sergiocontreras.masseffectnavigation
 * Created by: Contr
 * On: 08/11/2026 at 18:30
 * Creado en Settings -> Editor -> File and Code Templates
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MassEffectNavigationApp(viewModel: GalaxyViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val backStack = rememberNavBackStack(GalaxyList)

    StarfieldBackground {
        // TODO T08 §9 NavigationBar: la barra inferior irá en la ranura bottomBar del Scaffold.
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = {
                        val top = backStack.last()
                        Text(
                            if (top is SystemList) uiState.galaxies.first { it.id == top.galaxyId }.name
                            else stringResource(titleFor(top))
                        )
                    },
                    navigationIcon = {
                        if (backStack.size > 1) { // Arriba nunca saca de la app
                            BackButton(onClick = { backStack.removeLastOrNull() })
                        }                    }
                )
            }
        ) { innerPadding ->
            NavDisplay(
                backStack = backStack,
                onBack = {
                    backStack.removeLastOrNull()
                    Log.d("GalaxyTracker", "Pila: ${backStack.toList()}")
                },
                modifier = Modifier.padding(innerPadding),
                entryProvider = entryProvider {
                    entry<GalaxyList> {
                        GalaxyListScreen(
                            galaxies = uiState.galaxies,
                            systems = uiState.systems,
                            onGalaxyClick = { galaxy -> backStack.add(SystemList(galaxy.id)) }
                        )
                    }
                    entry<SystemList> { systemList ->
                        SystemListScreen(
                            galaxy = uiState.galaxies.first { it.id == systemList.galaxyId},
                            systems = uiState.systems.filter { it.galaxyId == systemList.galaxyId },
                            onToggleExplored = {
                                system -> viewModel.toggleExplored(system.id)
                            }
                        )
                    }
                    entry<Progress> {
                        ProgressScreen(
                            galaxies = uiState.galaxies,
                            systems = uiState.systems,
                            onGalaxyClick = { galaxy -> backStack.add(SystemList(galaxy.id)) }
                        )
                    }
                    
                }
            )
        }
    }
}

@StringRes
private fun titleFor(key: NavKey): Int = when (key) {
    is SystemList -> R.string.systems_title
    Progress -> R.string.progress_title
    else -> R.string.app_name
}