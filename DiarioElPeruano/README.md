Diario El Peruano es una aplicación Android construida con Kotlin y Jetpack Compose, que permite acceder de forma organizada y rápida a las distintas secciones del diario oficial del Perú a través de un menú lateral interactivo. Utiliza componentes modernos de Material 3.

Características principales

✅ Pantalla Splash personalizada con animación y logo institucional.

✅ Menú lateral (Navigation Drawer) con categorías y subcategorías.

✅ Interfaz moderna y responsiva con Material 3.

✅ Carga dinámica de contenido desde la web mediante WebView.

✅ Mensaje central cuando no hay sección seleccionada.

Splash Screen

La pantalla de inicio se muestra durante unos segundos con una animación de escalado y luego redirige automáticamente al menú principal. Se puede también acceder presionando el botón.

Menú de navegación lateral
El menú usa ModalNavigationDrawer e incluye tanto opciones simples como con submenús (MenuItem.Simple y MenuItem.WithSubItems). Cada elemento redirige a su respectivo enlace en el WebView.

WebView central
Cuando se selecciona una categoría del menú, se carga su contenido mediante WebView. Si aún no se ha elegido nada, se muestra un mensaje centrado con fondo degradado para guiar al usuario.
