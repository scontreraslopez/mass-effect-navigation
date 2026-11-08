package net.iessochoa.sergiocontreras.masseffectnavigation.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import net.iessochoa.sergiocontreras.masseffectnavigation.data.GalaxyData

/**
 * Project: Mass Effect Navigation
 * From: net.iessochoa.sergiocontreras.masseffectnavigation.ui
 * Created by: Contr
 * On: 08/11/2026 at 22:13
 * Creado en Settings -> Editor -> File and Code Templates
 */
class GalaxyViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(GalaxyUiState(
        galaxies = GalaxyData.galaxies,
        systems = GalaxyData.systems
    ))
    val uiState: StateFlow<GalaxyUiState> = _uiState.asStateFlow()

    fun toggleExplored(systemId: Int) {
        _uiState.update { state ->
            state.copy(systems = state.systems.map {
                if (it.id == systemId) it.copy(explored = !it.explored) else it }
            )
        }
    }

}