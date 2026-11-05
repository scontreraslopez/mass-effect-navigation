package net.iessochoa.sergiocontreras.masseffectnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import net.iessochoa.sergiocontreras.masseffectnavigation.data.GalaxyData
import net.iessochoa.sergiocontreras.masseffectnavigation.ui.screens.galaxies.GalaxyListScreen
import net.iessochoa.sergiocontreras.masseffectnavigation.ui.theme.MassEffectNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MassEffectNavigationTheme {
                // TODO Navigation 3: sustituir por el NavDisplay con el back stack.
                // TODO Estado: los datos salen "a pelo" de GalaxyData, sin ViewModel.
                GalaxyListScreen(
                    galaxies = GalaxyData.galaxies,
                    systems = GalaxyData.systems,
                    onGalaxyClick = { /* TODO navegar a SystemListScreen */ },
                    onProgressClick = { /* TODO navegar a ProgressScreen */ },
                )
            }
        }
    }
}
