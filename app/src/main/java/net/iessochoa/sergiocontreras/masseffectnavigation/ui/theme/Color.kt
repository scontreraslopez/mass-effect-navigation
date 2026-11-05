package net.iessochoa.sergiocontreras.masseffectnavigation.ui.theme

import androidx.compose.ui.graphics.Color

val SpaceBlack = Color(0xFF060A14)
val DeepSpace = Color(0xFF0B1430)
val PanelBlue = Color(0xFF111A2E)
val PanelBlueHigh = Color(0xFF1A2540)
val ParagonBlue = Color(0xFF4FA3FF)
val RenegadeRed = Color(0xFFE5484D)
val EezoTeal = Color(0xFF3DD6C4)
val StarWhite = Color(0xFFE8EEFA)
val MutedText = Color(0xFF8A98B8)

/** Un color de acento por galaxia, para que cada una tenga su "personalidad". */
private val GalaxyAccents = listOf(
    Color(0xFF4FA3FF), // azul
    Color(0xFFFFB547), // ámbar
    Color(0xFFB57BFF), // violeta
    Color(0xFFE5484D), // rojo
    Color(0xFF3DD6C4), // turquesa
)

fun galaxyAccent(galaxyId: Int): Color = GalaxyAccents[galaxyId % GalaxyAccents.size]
