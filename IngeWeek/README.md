# IngeWeek App

Aplicación oficial de la **Semana de la Ingeniería - UNS**, diseñada para ofrecer una agenda digital moderna, intuitiva y accesible. Desarrollada en **Kotlin** con el patrón **MVVM**, utilizando **Material Design 3**, `RecyclerView`, `AlertDialog`, `BottomNavigationView` y `Navigation Component`.

---

## 🧭 Funcionalidades principales

### 🏁 Pantalla de Bienvenida (Splash)
Al iniciar la app, se muestra una pantalla splash con el logotipo de la UNS durante unos segundos antes de redirigir a la pantalla principal.

![Image_alt](https://github.com/LeonardoDRR31/Aplicaciones-Moviles/blob/f5ff7fa22a158c20aa2ca98e9dc3005640ea024b/IngeWeek/Splash.jpeg)

---

### 🏠 Pantalla Principal
Contiene el nombre de la app y botones de acceso a cada sección: Agenda, Competencias, Seminarios y Ubicaciones.

---

### 📅 Agenda
Muestra la programación diaria de eventos organizada por pestañas (TabLayout). Cada día muestra sus actividades en tarjetas con horario, título y lugar.

![Agenda](https://github.com/LeonardoDRR31/Aplicaciones-Moviles/blob/f5ff7fa22a158c20aa2ca98e9dc3005640ea024b/IngeWeek/agenda.jpeg)

---

### 🏆 Competencias
Lista de competencias técnicas y recreativas, mostradas en un `RecyclerView` con tarjetas (`CardView`). Al tocar una, se muestra un `AlertDialog` con más información y opción para continuar.

![Competencia](https://github.com/LeonardoDRR31/Aplicaciones-Moviles/blob/f5ff7fa22a158c20aa2ca98e9dc3005640ea024b/IngeWeek/competencias.jpeg)

---

### 🎓 Seminarios
Lista filtrable de ponentes y seminarios. Incluye un `SearchView` para buscar por nombre. Al seleccionar un ponente, se despliega información detallada.

![Seminarios](https://github.com/LeonardoDRR31/Aplicaciones-Moviles/blob/f5ff7fa22a158c20aa2ca98e9dc3005640ea024b/IngeWeek/seminarios.jpeg)

---

### 📍 Ubicaciones
Muestra los espacios utilizados durante el evento: aulas, laboratorios y patios, con breve descripción.

![Ubicaciones](https://github.com/LeonardoDRR31/Aplicaciones-Moviles/blob/f5ff7fa22a158c20aa2ca98e9dc3005640ea024b/IngeWeek/ubicaciones.jpeg)

---

## 🧱 Arquitectura

- **MVVM** (Model-View-ViewModel)
- Uso de `LiveData`, `ViewModel`, y `RecyclerView`
- Separación de lógica por paquetes:
  - `ui/` (Actividades y adaptadores)
  - `data/` (Modelos y datos simulados)
  - `utils/` (utilidades generales)

---

## 🛠️ Tecnologías usadas

- Kotlin
- Android Studio
- Material Design 3
- RecyclerView + CardView
- Navigation Component
- AlertDialog
- ViewModel & LiveData


