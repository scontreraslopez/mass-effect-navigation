package net.iessochoa.sergiocontreras.masseffectnavigation.data

import net.iessochoa.sergiocontreras.masseffectnavigation.model.Galaxy
import net.iessochoa.sergiocontreras.masseffectnavigation.model.StarSystem

/**
 * Datos mock de la app. Nada de red ni de BD: listas en memoria.
 */
object GalaxyData {

    val galaxies = listOf(
        Galaxy(1, "Serpent Nebula", "Corazón político de la galaxia y sede de la Ciudadela."),
        Galaxy(2, "Attican Traverse", "Región fronteriza llena de colonias recientes y piratas."),
        Galaxy(3, "Hourglass Nebula", "Nebulosa inestable con relés que nadie quiere cruzar."),
        Galaxy(4, "Terminus Systems", "Sin ley ni Consejo. Mercenarios, contrabando y Omega."),
        Galaxy(5, "Arcturus Stream", "Bastión de la Alianza y cuartel general de su flota."),
    )

    val systems = listOf(
        // Serpent Nebula
        StarSystem(101, 1, "Widow", "Sistema de la Ciudadela. Tráfico constante.", 3),
        StarSystem(102, 1, "Vallum", "Rutas comerciales hacia el espacio turiano.", 4),
        StarSystem(103, 1, "Fortis", "Gigantes gaseosos y estaciones de repostaje.", 5),

        // Attican Traverse
        StarSystem(201, 2, "Knossos", "Ruinas protheanas a medio excavar.", 4),
        StarSystem(202, 2, "Asgard", "Colonias agrícolas expuestas a incursiones.", 3),
        StarSystem(203, 2, "Plato", "Cinturón de asteroides rico en eezo.", 6),
        StarSystem(204, 2, "Dis", "Señales de baliza sin identificar.", 2),

        // Hourglass Nebula
        StarSystem(301, 3, "Ploitari", "Radiación elevada. Escudos al máximo.", 4),
        StarSystem(302, 3, "Faia", "Planetas helados y estaciones abandonadas.", 3),
        StarSystem(303, 3, "Sowilo", "Una estrella moribunda y un relé cercano.", 5),

        // Terminus Systems
        StarSystem(401, 4, "Sahrabarik", "Hogar de Omega. Paga en efectivo.", 3),
        StarSystem(402, 4, "Hawking Eta", "Laboratorios ilegales escondidos en lunas.", 4),
        StarSystem(403, 4, "Eagle Nebula", "Corredor de contrabandistas.", 5),
        StarSystem(404, 4, "Xe Cha", "Ruta de reclutamiento de mercenarios.", 2),

        // Arcturus Stream
        StarSystem(501, 5, "Arcturus", "Estación Arcturus y astilleros de la Alianza.", 2),
        StarSystem(502, 5, "Sol", "El sistema de origen de la humanidad.", 8),
    )
}
