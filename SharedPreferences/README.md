# 📱 Proyecto: Uso de SharedPreferences en Android

Este proyecto de Android demuestra cómo utilizar `SharedPreferences` para almacenar y recuperar datos persistentes como nombre de usuario, correo electrónico, edad, contador de visitas, tema oscuro/claro y selección de imagen de perfil.

---

## 🧩 Funcionalidades Principales

### 🏠 MainActivity
- Guardar y mostrar el nombre del usuario.
- Recordar si es la primera vez que se abre la aplicación.
- Contador de visitas (incrementa automáticamente cada vez que se abre la app).
- Tema claro/oscuro con persistencia usando un switch.
- Navegación a LoginActivity.
- Reinicio del contador de visitas.
- Limpieza de datos del usuario (sin afectar el contador).

### 🔐 LoginActivity
- Guardar nombre, correo electrónico y edad.
- Validación de edad (entre 16 y 120 años).
- Selección de dominio de correo con un `Spinner`.
- Visualización de los datos guardados.
- Limpieza completa de datos guardados.
- Selección de imagen de perfil desde la galería.

---

## 🛠️ Tecnologías Usadas

- Kotlin
- Android SDK
- SharedPreferences
- AppCompatDelegate (modo oscuro)
- Jetpack ActivityResult (selección de imagen)
- XML Layouts

---

---

## 📸 Capturas de Pantalla

### 🔢 Contador de visitas

| Inicio | Cargar datos | Tema claro |
|--------|-------------|---------------------|
| ![count1](https://github.com/LeonardoDRR31/Aplicaciones-Moviles/blob/9063ab2350a15cee29d67696e94ad023f92cee9a/SharedPreferences/count1.jpeg?raw=true) | ![count2](https://github.com/LeonardoDRR31/Aplicaciones-Moviles/blob/9063ab2350a15cee29d67696e94ad023f92cee9a/SharedPreferences/count2.jpeg?raw=true) | ![count3](https://github.com/LeonardoDRR31/Aplicaciones-Moviles/blob/9063ab2350a15cee29d67696e94ad023f92cee9a/SharedPreferences/count3.jpeg?raw=true) |

### 🧑 Registro de usuario

| Formulario vacío | Dominio seleccionado | Cargar datos |
|------------------|---------------------|-----------------------|
| ![login1](https://github.com/LeonardoDRR31/Aplicaciones-Moviles/blob/d7162d86068fe3d15c0e18c2f42dbbba1fec5efa/SharedPreferences/login1.jpeg?raw=true) | ![login2](https://github.com/LeonardoDRR31/Aplicaciones-Moviles/blob/d7162d86068fe3d15c0e18c2f42dbbba1fec5efa/SharedPreferences/login2.jpeg?raw=true) | ![login3](https://github.com/LeonardoDRR31/Aplicaciones-Moviles/blob/d7162d86068fe3d15c0e18c2f42dbbba1fec5efa/SharedPreferences/login3.jpeg?raw=true) |

| Datos limpiados | Campos limpios |
|----------------|----------------|
| ![login4](https://github.com/LeonardoDRR31/Aplicaciones-Moviles/blob/d7162d86068fe3d15c0e18c2f42dbbba1fec5efa/SharedPreferences/login4.jpeg?raw=true) | ![login5](https://github.com/LeonardoDRR31/Aplicaciones-Moviles/blob/d7162d86068fe3d15c0e18c2f42dbbba1fec5efa/SharedPreferences/login5.jpeg?raw=true) |




