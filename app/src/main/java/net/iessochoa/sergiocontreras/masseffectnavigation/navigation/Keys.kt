package net.iessochoa.sergiocontreras.masseffectnavigation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object GalaxyList: NavKey                    // La lista de galaxias: el destino de inicio
@Serializable
data class SystemList(val galaxyId: Int): NavKey  // Los sistemas de una galaxia
@Serializable
object Progress: NavKey                      // El progreso de exploración
