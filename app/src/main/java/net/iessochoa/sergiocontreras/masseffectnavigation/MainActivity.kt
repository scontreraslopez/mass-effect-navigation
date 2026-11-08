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
                MassEffectNavigationApp()
            }
        }
    }
}
