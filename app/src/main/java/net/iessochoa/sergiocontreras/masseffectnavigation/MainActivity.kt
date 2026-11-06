package net.iessochoa.sergiocontreras.masseffectnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import net.iessochoa.sergiocontreras.masseffectnavigation.data.GalaxyData
import net.iessochoa.sergiocontreras.masseffectnavigation.ui.components.StarfieldBackground
import net.iessochoa.sergiocontreras.masseffectnavigation.ui.screens.galaxies.GalaxyListScreen
import net.iessochoa.sergiocontreras.masseffectnavigation.ui.theme.MassEffectNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MassEffectNavigationTheme {
                // TODO T08 §6 TopAppBar: este StarfieldBackground y este Scaffold pasan a GalaxyTrackerApp,
                //  con la TopAppBar global. Aquí solo quedará la llamada a GalaxyTrackerApp().
                StarfieldBackground {
                    // TODO T08 §9 NavigationBar: la barra inferior irá en la ranura bottomBar del Scaffold.
                    Scaffold(containerColor = Color.Transparent) { innerPadding ->
                        // TODO T08 §4 NavDisplay: sustituye esta pantalla por GalaxyTrackerApp, con la pila y el NavDisplay.
                        // TODO T08 §7 rememberNavBackStack: guarda la pila con rememberNavBackStack para que sobreviva al giro.
                        // TODO T08 §8 ViewModel: los datos salen directamente de GalaxyData; llévalos a GalaxyViewModel.
                        GalaxyListScreen(
                            galaxies = GalaxyData.galaxies,
                            systems = GalaxyData.systems,
                            onGalaxyClick = { /* TODO T08 §4 NavDisplay: abrir los sistemas de la galaxia */ },
                            modifier = Modifier.padding(innerPadding),
                        )
                    }
                }
            }
        }
    }
}
