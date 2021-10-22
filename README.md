# Android Compose App

## Herramientas utilizadas

### Lenguaje
* [Kotlin](https://kotlinlang.org/)
### Persistencia
* [Room](https://developer.android.com/jetpack/androidx/releases/room?hl=en)
### Conectividad
* [Ktor](https://ktor.io/)
### Interfaz de Usuario
* [Compose](https://developer.android.com/jetpack/compose)
* [Accompanist](https://google.github.io/accompanist/)
* [Coil](https://coil-kt.github.io/coil/)
### Testing
* [JUnit](https://junit.org/junit5/)

## Estructura de las carpetas

- domain: lógica de la aplicación
- ui: aplicaciones composables para la interfaz de usuario
  
### Subestructura de la carpeta domain
- data: contiene las funciones que cargan datos desde una API externa, así como el objeto que funge como viewModel en este proyecto.
- database: contiene la definición de la base de datos local, los objetos de la misma, y sus respectivos data access object (DAO).
- misc: contiene funciones concernientes a las notificaciones, y una clase sellada para almacenar las rutas de navegación.

### Subestructura de la carpeta ui
- composables: contiene las funciones que generan partes de una vista, así como también la barra inferior, superior, y el controlador de navegación que une toda la aplicación.
- theme: contiene toda la información de los componentes de materialTheme, como lo son los colores, la tipografía, y las formas a crear.
- views: la piedra angular de la aplicación, el lugar donde se almacenan las vistas, compuestas de funciones composables modularizadas, y separadas en vistas premenú y menú.