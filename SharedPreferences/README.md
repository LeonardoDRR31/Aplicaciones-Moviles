SharedPreferences Android App 📱

Esta aplicación Android desarrollada en Kotlin demuestra el uso de SharedPreferences para guardar datos del usuario de forma persistente. Incluye gestión de temas (modo oscuro/claro), conteo de visitas, registro con correo electrónico personalizable y navegación entre pantallas.

🌐 Características

Guardado y carga de datos con SharedPreferences

Cambio entre modo claro y oscuro con Switch

Registro de datos del usuario: nombre, correo, edad, ID

Armado de correo electrónico con Spinner de dominios

Selección de foto de perfil

Contador de visitas con reinicio manual

Interfaz moderna con ConstraintLayout y LinearLayout

📁 Estructura del proyecto

com.example.sharedpreferences
├── MainActivity.kt
├── LoginActivity.kt
├── SharedPreferencesHelper.kt
├── EmailDomains.kt
├── res/
│   └── layout/
│       ├── activity_main.xml
│       └── activity_login.xml
│   └── values/
│       └── strings.xml

🚀 Tecnologías utilizadas

Kotlin

Android SDK

SharedPreferences

ConstraintLayout, LinearLayout

Spinner, Switch, Toast, ImageView

🔍 Explicación de componentes clave

SharedPreferencesHelper.kt

Clase auxiliar que centraliza el manejo de claves y operaciones para guardar/obtener datos como:

Nombre de usuario, correo, edad

Tema oscuro/claro

Contador de visitas

Identificador de usuario

MainActivity.kt

Pantalla principal de la app

Permite ingresar el nombre, cambiar de tema, ver contador de visitas y navegar al registro

LoginActivity.kt

Registro completo del usuario: nombre, correo armado con dominio, edad y foto

Guardado persistente de los datos del usuario

EmailDomains.kt

Objeto que contiene una lista fija de dominios de correo disponibles:

val list = listOf("@gmail.com", "@hotmail.com", "@uns.edu.pe", "@outlook.com")

UI en XML

EditText para nombre, edad, nombre de correo

Spinner para elegir el dominio del correo

ImageView para seleccionar imagen de perfil

TextView para mostrar los datos

🔄 Flujo de la app

El usuario inicia la app por primera vez, se guarda el estado con una bandera isFirstTime

Elige modo claro/oscuro y se guarda esa preferencia

Se puede guardar nombre y navegar al registro

En registro, se arma correo con EditText + Spinner

Se guarda nombre, correo completo y edad

Se puede cargar o limpiar datos

📷 Capturas de pantalla

🏠 MainActivity (pantalla principal)



👤 LoginActivity (registro del usuario)



🚪 Requisitos

Android Studio Flamingo o superior

SDK mínimo: API 21 (Android 5.0)

📅 Autor

Leonardo RojasDesarrollador Android - 2025

Proyecto creado con fines educativos para practicar persistencia de datos y personalización de UI en Android.