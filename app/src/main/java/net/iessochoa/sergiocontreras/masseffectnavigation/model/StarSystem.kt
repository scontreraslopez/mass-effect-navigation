package net.iessochoa.sergiocontreras.masseffectnavigation.model

data class StarSystem(
    val id: Int,
    val galaxyId: Int,
    val name: String,
    val description: String,
    val planets: Int,
    val explored: Boolean = false,
)
