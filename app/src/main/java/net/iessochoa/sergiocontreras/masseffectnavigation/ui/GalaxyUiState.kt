package net.iessochoa.sergiocontreras.masseffectnavigation.ui

import net.iessochoa.sergiocontreras.masseffectnavigation.model.Galaxy
import net.iessochoa.sergiocontreras.masseffectnavigation.model.StarSystem

data class GalaxyUiState(
    val galaxies: List<Galaxy> = emptyList(),
    val systems: List<StarSystem> = emptyList()
)
