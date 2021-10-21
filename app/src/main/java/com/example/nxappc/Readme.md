# Android Compose App

## Estructura de las carpetas

- composables: funciones que dan como resultado partes de la vista
- data: función auxiliar para cargar los datos del JSON
- objects: contiene las clases auxiliares de product (para deserialización del JSON) y las rutas del
  controlador de navegación
- state: el viewModel contenedor de la información del carrito
- theme: toda la información de los componentes de materialTheme, como lo son los colores, la
  tipografía, y las formas a crear
- views: la piedra angular de la aplicación, el lugar donde se almacenan las vistas, compuestas de
  funciones composables modularizadas

# Funciones de las carpetas

## Composables

- BottomNavigationBar: componente de la barra inferior que sirve como navegación dentro de la
  aplicación
- CartCard: componente modular para la creación de cartas dentro del carrito
- ErrorInput: componente simple para mensajes de error
- NavigationController: componente que funciona como el punto de partida de la aplicación y su grafo
  de navegación
- ProductCard: componente modular para la creación de cartas dentro del menú. Similar a CartCard,
  pero con más información
- RatingGenerator: componente auxiliar para la creación de barras de ratings
- TopAppBar: componente de la barra superior persistente de la app, para la función de búsqueda y
  más información
- UserField: componente modular para crear cajas de introducción de texto

## Data

- LoadData: toma la información que está dentro del JSON y la deserializa a una ArrayList<Product>
  con ayuda de GSON y la data class product, de forma que el JSON quede en un formato fácilmente
  usable y manejable

## Objects

- Product: funciona como data class para deserialización del JSON
- Routes: funciona como una clase sellada, que creará singletons que dentro contiene la string de la
  ruta relevante

## State

- ProductModel: funciona como viewModel persistente, el cual tiene como finalidad contener la
  información que deba poseer el carrito de compras

## Views

- Login: la vista principal, y el punto de partida de la información
- SignIn: la vista de registro
- MainMenu: la vista general de los productos dentro del JSON
- ProductDetail: los detalles del producto seleccionado en la vista anterior
- ShoppingCart: la vista que contendrá la información de los productos seleccionados en la vista de
  ProductDetail
- UserProfile: la vista que funcionaría como información del perfil del usuario final