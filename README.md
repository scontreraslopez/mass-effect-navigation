# Galaxy Tracker

Un mapa galáctico al estilo de Mass Effect: una lista de galaxias, los sistemas de cada una (que marcas como explorados) y una pantalla con tu progreso de exploración.

Es el **proyecto de partida** del ejercicio *Galaxy Tracker* del tema **T08 · Navegación** de PMDM. Las pantallas, el modelo, los datos y los componentes ya están hechos; falta conectarlo todo con Navigation 3: la pila, el `NavDisplay`, la barra superior, la barra inferior y el ViewModel. Los `// TODO` de `MainActivity.kt` dicen qué sección del tema va en cada sitio.

La solución completa está en la rama `solucion`, con un commit por cada paso del ejercicio.

## Modelo de datos

Dos `data class` en el paquete `model`, relacionadas por id (como haríamos con una clave ajena en una BD):

```kotlin
data class Galaxy(
    val id: Int,
    val name: String,
    val description: String,
)

data class StarSystem(
    val id: Int,
    val galaxyId: Int,          // a qué galaxia pertenece
    val name: String,
    val description: String,
    val planets: Int,
    val explored: Boolean = false,
)
```

- Una galaxia **no** guarda la lista de sus sistemas: se obtienen filtrando `systems.filter { it.galaxyId == galaxy.id }`.
- `explored` es el único dato que cambia mientras se usa la app. Como las `data class` son inmutables, marcar un sistema significa sustituirlo por `system.copy(explored = !system.explored)` dentro de la lista.
- El progreso (cuántos explorados de cuántos, el porcentaje) **no se guarda**: se calcula a partir de la lista de sistemas cada vez que se pinta.

Los datos de prueba están en `data/GalaxyData.kt`: un `object` con dos listas en memoria (5 galaxias y 16 sistemas). No hay red ni base de datos. Ahora mismo `MainActivity` lee esas listas directamente; en el ejercicio pasarán a un `GalaxyViewModel` que las expone como estado y sabe cambiar `explored`.

## Pantallas

Las tres pantallas están en `ui/screens` y son **stateless**: reciben los datos por parámetro y avisan hacia arriba con lambdas. No tienen `Scaffold` ni fondo propios, porque la barra superior, la inferior y el fondo de estrellas son comunes a toda la app.

| Pantalla | Fichero | Recibe | Avisa con |
|---|---|---|---|
| Lista de galaxias | `galaxies/GalaxyListScreen.kt` | `galaxies`, `systems` | `onGalaxyClick(galaxy)` |
| Sistemas de una galaxia | `systems/SystemListScreen.kt` | `galaxy`, `systems` (ya filtrados) | `onToggleExplored(system)` |
| Progreso de exploración | `progress/ProgressScreen.kt` | `galaxies`, `systems` | `onGalaxyClick(galaxy)` |

1. **Lista de galaxias.** Una tarjeta por galaxia con su descripción y su barra de progreso. Al pulsar una se debería abrir su lista de sistemas.
2. **Sistemas.** La descripción de la galaxia, su progreso y la lista de sistemas. Al pulsar un sistema se marca o desmarca como explorado.
3. **Progreso.** Un indicador circular con el porcentaje global y el desglose por galaxia. Pulsar una galaxia también lleva a sus sistemas.

El flujo de navegación que hay que montar queda así:

```
 ┌─────────────┐   pulsar galaxia   ┌──────────────┐
 │  Galaxias   │ ─────────────────▶ │   Sistemas   │
 └─────────────┘                    └──────────────┘
        ▲                                  ▲
        │  barra inferior                  │ pulsar galaxia
        ▼                                  │
 ┌─────────────┐ ──────────────────────────┘
 │  Progreso   │
 └─────────────┘
```

*Galaxias* y *Progreso* son las dos pestañas de la barra inferior; *Sistemas* es una pantalla de detalle que se apila encima y necesita saber de qué galaxia se trata (su id viaja en la clave de navegación).

## Componentes

En `ui/components` hay piezas reutilizables que usan las pantallas:

- `StarfieldBackground`: fondo negro con estrellas dibujadas en un `Canvas`. Va una sola vez, envolviendo toda la app.
- `GalaxyCard`: tarjeta de una galaxia en la lista principal.
- `StarSystemItem`: fila de un sistema, con el número de planetas y la marca de explorado.
- `ExplorationProgress`: barra lineal con el texto "x / total sistemas · NN%".
- `BackButton`: flecha de volver, pensada para la `TopAppBar`.

Cada galaxia tiene un color de acento propio que se obtiene con `galaxyAccent(galaxy.id)` (en `ui/theme/Color.kt`), para que la tarjeta, sus sistemas y su barra de progreso vayan a juego. Todos los componentes y pantallas tienen `@Preview`, así que se pueden ver en Android Studio sin ejecutar la app.

## Estructura del proyecto

```
masseffectnavigation/
├── MainActivity.kt          ← aquí están los TODO del ejercicio
├── model/                   ← Galaxy, StarSystem
├── data/                    ← GalaxyData (datos mock)
└── ui/
    ├── components/          ← piezas reutilizables
    ├── screens/
    │   ├── galaxies/
    │   ├── systems/
    │   └── progress/
    └── theme/               ← colores, tipografía y tema
```

Los textos de la interfaz (títulos de las barras, etiquetas de las pestañas, formatos de porcentaje y contadores) ya están en `res/values/strings.xml`.
