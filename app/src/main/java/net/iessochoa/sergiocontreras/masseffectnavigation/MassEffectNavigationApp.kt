package net.iessochoa.sergiocontreras.masseffectnavigation

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import net.iessochoa.sergiocontreras.masseffectnavigation.data.GalaxyData
import net.iessochoa.sergiocontreras.masseffectnavigation.navigation.GalaxyList
import net.iessochoa.sergiocontreras.masseffectnavigation.navigation.Progress
import net.iessochoa.sergiocontreras.masseffectnavigation.navigation.SystemList
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

@Composable
fun MassEffectNavigationApp() {

    val backStack = remember { mutableStateListOf<Any>(GalaxyList)}
    // TODO T08 §6 TopAppBar: este StarfieldBackground y este Scaffold pasan a GalaxyTrackerApp,
    //  con la TopAppBar global. Aquí solo quedará la llamada a GalaxyTrackerApp().
    StarfieldBackground {
        // TODO T08 §9 NavigationBar: la barra inferior irá en la ranura bottomBar del Scaffold.
        Scaffold(containerColor = Color.Transparent) { innerPadding ->
            // TODO T08 §4 NavDisplay: sustituye esta pantalla por GalaxyTrackerApp, con la pila y el NavDisplay.
            // TODO T08 §7 rememberNavBackStack: guarda la pila con rememberNavBackStack para que sobreviva al giro.
            // TODO T08 §8 ViewModel: los datos salen directamente de GalaxyData; llévalos a GalaxyViewModel.
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
                            galaxies = GalaxyData.galaxies,
                            systems = GalaxyData.systems,
                            onGalaxyClick = { galaxy -> backStack.add(SystemList(galaxy.id)) }
                        )
                    }
                    entry<SystemList> { systemList ->
                        SystemListScreen(
                            galaxy = GalaxyData.galaxies.first { it.id == systemList.galaxyId},
                            systems = GalaxyData.systems.filter { it.galaxyId == systemList.galaxyId },
                            onToggleExplored = {  }
                        )
                    }
                    entry<Progress> {
                        ProgressScreen(
                            galaxies = GalaxyData.galaxies,
                            systems = GalaxyData.systems,
                            onGalaxyClick = { galaxy -> backStack.add(SystemList(galaxy.id)) }
                        )
                    }
                    
                }
            )
        }
    }
}