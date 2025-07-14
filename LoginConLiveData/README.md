# 📱 UNS Login App

**UNS Login** es una aplicación móvil desarrollada para gestionar de manera segura el inicio de sesión y registro de usuarios pertenecientes a la **Universidad Nacional del Sur (UNS)**. Esta app implementa autenticación basada en correos institucionales, validación en tiempo real y una experiencia moderna con diseño visual atractivo y animaciones.

---

## 🧩 Características principales

- 🔐 **Inicio de sesión seguro** con Firebase Authentication
- ✅ **Validación en tiempo real** del correo con dominio `@uns.edu.pe`
- 📝 **Registro de nuevos usuarios** en Cloud Firestore
- 🔄 **Recuperación de contraseña** vía correo electrónico
- 🛡️ **Bloqueo automático** de cuenta tras 3 intentos fallidos de ingreso
- 👁️ **Visualización/ocultación de contraseña**
- 🧠 **Arquitectura MVVM** utilizando `ViewModel` y `LiveData`
- ✨ **Animaciones Lottie** para interacción visual moderna
- 👋 **Saludo personalizado** con nombre extraído desde Firestore al iniciar sesión

---

## 🖼️ Capturas de pantalla

| Pantalla de Inicio | Validación en tiempo real | Registro | Dashboard |
|--------------------|---------------------------|----------|-----------|
| ![Login](https://raw.githubusercontent.com/LeonardoDRR31/Aplicaciones-Moviles/bb563ac05599a3e41113898b85a09231a7e8689a/LoginConLiveData/assets/login_principal.jpeg) | ![Validación](https://raw.githubusercontent.com/LeonardoDRR31/Aplicaciones-Moviles/64822d9fe439034f10b2f5452fe60fe52c65b8c7/LoginConLiveData/assets/validacion_correo.jpeg) | ![Registro](https://raw.githubusercontent.com/LeonardoDRR31/Aplicaciones-Moviles/1dd79678b8ad476c9c7e7bb4066368e84df05665/LoginConLiveData/assets/registro.jpeg) | ![Dashboard](https://raw.githubusercontent.com/LeonardoDRR31/Aplicaciones-Moviles/1dd79678b8ad476c9c7e7bb4066368e84df05665/LoginConLiveData/assets/pantalla_bienvenida.jpeg) |

---

## ⚙️ Tecnologías utilizadas

- 🎯 **Lenguaje:** Kotlin
- 🔥 **Backend:** Firebase Authentication + Firebase Firestore
- 🧩 **Arquitectura:** MVVM (`ViewModel`, `LiveData`)
- 🧱 **UI/UX:** Material Design
- 🌀 **Animaciones:** Lottie de [Airbnb](https://airbnb.io/lottie/#/)
- 📋 **Validación de campos:** en tiempo real con `TextWatcher`

---

## 🚀 Cómo ejecutar el proyecto

1. **Clona este repositorio**  
   ```bash
   git clone https://github.com/LeonardoDRR31/UNS-Login-App.git
