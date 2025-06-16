Diario El Peruano es una aplicación Android construida con Kotlin y Jetpack Compose, que permite acceder de forma organizada y rápida a las distintas secciones del diario oficial del Perú a través de un menú lateral interactivo. Utiliza componentes modernos de Material 3.

Características principales

✅ Pantalla Splash personalizada con animación y logo institucional.

✅ Menú lateral (Navigation Drawer) con categorías y subcategorías.

✅ Interfaz moderna y responsiva con Material 3.

✅ Carga dinámica de contenido desde la web mediante WebView.

✅ Mensaje central cuando no hay sección seleccionada.

Splash Screen

La pantalla de inicio se muestra durante unos segundos con una animación de escalado y luego redirige automáticamente al menú principal. Se puede también acceder presionando el botón.

![Splash](https://github.com/LeonardoDRR31/Aplicaciones-Moviles/blob/f34f4268e1c828706c8b69a4d7ebb19ad194a329/DiarioElPeruano/Splash.jpeg)

Menú de navegación lateral

El menú usa ModalNavigationDrawer e incluye tanto opciones simples como con submenús (MenuItem.Simple y MenuItem.WithSubItems). Cada elemento redirige a su respectivo enlace en el WebView.

![Menu1](https://github.com/LeonardoDRR31/Aplicaciones-Moviles/blob/9d5a48c330452877b049eae4aba30233a884646c/DiarioElPeruano/Menu1.jpeg)
![Menu2](https://github.com/LeonardoDRR31/Aplicaciones-Moviles/blob/9d5a48c330452877b049eae4aba30233a884646c/DiarioElPeruano/Menu2.jpeg)
![Webview1](https://github.com/LeonardoDRR31/Aplicaciones-Moviles/blob/9d5a48c330452877b049eae4aba30233a884646c/DiarioElPeruano/Webview1.jpeg)
![Webview2](https://github.com/LeonardoDRR31/Aplicaciones-Moviles/blob/9d5a48c330452877b049eae4aba30233a884646c/DiarioElPeruano/Webview2.jpeg)

WebView central

Cuando se selecciona una categoría del menú, se carga su contenido mediante WebView. Si aún no se ha elegido nada, se muestra un mensaje centrado con fondo degradado para guiar al usuario.

![WebviewCentral](https://github.com/LeonardoDRR31/Aplicaciones-Moviles/blob/9d5a48c330452877b049eae4aba30233a884646c/DiarioElPeruano/WebviewCentral.jpeg)
