# 📰 Diario El Peruano - App Android

**Diario El Peruano** es una aplicación Android desarrollada en **Kotlin** utilizando **Jetpack Compose** y **Material 3**, que permite acceder de forma rápida y estructurada a las distintas secciones del diario oficial del Perú. Cuenta con una interfaz moderna, responsiva y un menú lateral interactivo.

---

## ✨ Características principales

- ✅ **Pantalla Splash** personalizada con animación y logo institucional.
- ✅ **Menú lateral (Navigation Drawer)** con categorías y subcategorías.
- ✅ **Interfaz moderna y responsiva** basada en Material 3.
- ✅ **Carga dinámica de contenido** mediante WebView.
- ✅ **Mensaje de bienvenida** cuando no hay sección seleccionada.

---

## 🟦 Pantalla Splash

Al iniciar la app, se muestra una pantalla con animación de escalado sobre el logo del diario, dando paso automáticamente al menú principal. También se puede omitir tocando el botón.

![Splash](https://github.com/LeonardoDRR31/Aplicaciones-Moviles/blob/f34f4268e1c828706c8b69a4d7ebb19ad194a329/DiarioElPeruano/Splash.jpeg)

---

## 📂 Menú de navegación lateral

Utiliza `ModalNavigationDrawer` con soporte para:

- 🔹 Ítems simples (`MenuItem.Simple`)
- 🔸 Ítems con submenús (`MenuItem.WithSubItems`)

Cada opción carga su respectivo contenido mediante WebView.

### Vista del menú abierto:

![Menu1](https://github.com/LeonardoDRR31/Aplicaciones-Moviles/blob/29d06f16edb2bb2612dd810e277f885d9c7d02ab/DiarioElPeruano/Menu1.jpeg)
![Menu2](https://github.com/LeonardoDRR31/Aplicaciones-Moviles/blob/29d06f16edb2bb2612dd810e277f885d9c7d02ab/DiarioElPeruano/Menu2.jpeg)

---

## 🌐 Vista Web (WebView)

Cuando se selecciona una categoría, su enlace se carga directamente en un `WebView`. Si no se ha elegido ninguna, se muestra un mensaje simple indicandote elegir una opción del menú.

### Ejemplos de contenido cargado:

![Webview1](https://github.com/LeonardoDRR31/Aplicaciones-Moviles/blob/9d5a48c330452877b049eae4aba30233a884646c/DiarioElPeruano/Webview1.jpeg)
![Webview2](https://github.com/LeonardoDRR31/Aplicaciones-Moviles/blob/9d5a48c330452877b049eae4aba30233a884646c/DiarioElPeruano/Webview2.jpeg)

### Mensaje cuando no hay selección:

![WebviewCentral](https://github.com/LeonardoDRR31/Aplicaciones-Moviles/blob/9d5a48c330452877b049eae4aba30233a884646c/DiarioElPeruano/WebviewCentral.jpeg)

---

## 🛠️ Tecnologías utilizadas

- 🧩 Kotlin
- 🧱 Jetpack Compose
- 🎨 Material Design 3
- 🌐 Android WebView
- 🔄 Navegación con estados y `remember`

---

## 📦 Estructura general del proyecto


