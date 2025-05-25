package com.example.s4rojasrojas

object PreguntaRepositorio {
    val preguntas = listOf(
        Pregunta(
            "¿Cuál de las siguientes es una ventaja de las aplicaciones móviles nativas?",
            listOf(
                "Mayor rendimiento y acceso a características del dispositivo",
                "Compatibilidad con todos los sistemas operativos",
                "No requieren conexión a internet",
                "Son más fáciles de desarrollar que las aplicaciones web"
            ),
            0
        ),
        Pregunta(
            "¿Cuál es la principal diferencia entre una aplicación móvil nativa y una híbrida?",
            listOf(
                "Las aplicaciones nativas funcionan solo en un sistema operativo, mientras que las híbridas funcionan en varios.",
                "Las aplicaciones híbridas siempre requieren una conexión a internet, mientras que las nativas no.",
                "Las aplicaciones híbridas son más caras de desarrollar que las nativas.",
                "Las aplicaciones nativas tienen acceso limitado a los recursos del dispositivo, mientras que las híbridas tienen acceso completo."
            ),
            0
        ),
        Pregunta(
            "¿Qué es una PWA (Progressive Web App)?",
            listOf(
                "Una aplicación web que se instala en el dispositivo como una aplicación nativa",
                "Una aplicación móvil que no necesita conexión a internet",
                "Una aplicación diseñada solo para dispositivos iOS",
                "Un tipo de aplicación que solo funciona en dispositivos Android"
            ),
            0
        ),
        Pregunta(
            "¿Cuál de los siguientes es un beneficio principal de las aplicaciones móviles en comparación con los sitios web móviles?",
            listOf(
                "Las aplicaciones móviles siempre requieren menos recursos",
                "Las aplicaciones móviles pueden ofrecer una experiencia de usuario más fluida y accesible",
                "Las aplicaciones móviles solo pueden funcionar en dispositivos Android",
                "Las aplicaciones móviles nunca necesitan ser actualizadas"
            ),
            1
        ),
        Pregunta(
            "¿Cuál de las siguientes es una característica de las \"notificaciones push\" en las aplicaciones móviles?",
            listOf(
                "Solo se pueden enviar mensajes de texto",
                "Son enviadas a los usuarios incluso cuando la aplicación está cerrada",
                "Solo pueden ser enviadas a través de redes sociales",
                "Son parte de la interfaz gráfica de la aplicación"
            ),
            1
        ),
        Pregunta(
            "¿Cuál es el propósito de una API en el desarrollo de aplicaciones móviles?",
            listOf(
                "Crear la interfaz de usuario",
                "Conectar la aplicación con servicios externos y bases de datos",
                "Asegurar la privacidad de los datos del usuario",
                "Optimizar el rendimiento del dispositivo"
            ),
            1
        ),
        Pregunta(
            "¿Qué es el \"SDK\" en el desarrollo de aplicaciones móviles?",
            listOf(
                "Un lenguaje de programación",
                "Un conjunto de herramientas y bibliotecas para desarrollar aplicaciones",
                "Una plataforma para distribuir aplicaciones",
                "Un tipo de base de datos"
            ),
            1
        ),
        Pregunta(
            "¿Cuál es el propósito de una \"app sandbox\" en los dispositivos móviles?",
            listOf(
                "Mejorar el rendimiento de la aplicación",
                "Aislar y proteger los datos de la aplicación del resto del sistema operativo",
                "Permitir que la aplicación use más recursos del dispositivo",
                "Ejecutar aplicaciones solo en segundo plano"
            ),
            1
        ),
        Pregunta(
            "¿Cuál de las siguientes afirmaciones es cierta sobre las aplicaciones móviles?",
            listOf(
                "Solo las aplicaciones nativas pueden acceder a las cámaras y sensores del dispositivo",
                "Las aplicaciones móviles siempre deben estar conectadas a internet",
                "Las aplicaciones móviles híbridas no pueden utilizar bases de datos locales",
                "Las aplicaciones móviles deben ser descargadas desde la tienda de aplicaciones para funcionar"
            ),
            0
        ),
        Pregunta(
            "¿Qué tecnología permite que una aplicación móvil se sincronice con servicios en la nube?",
            listOf(
                "Bluetooth",
                "Wi-Fi",
                "APIs de nube y servicios web",
                "Sistema operativo"
            ),
            2
        ),
        Pregunta(
            "¿Qué significa \"debugging\" en el desarrollo de aplicaciones móviles?",
            listOf(
                "Agregar nuevas funciones a la aplicación",
                "Eliminar errores y fallos en el código de la aplicación",
                "Publicar la aplicación en la tienda de aplicaciones",
                "Hacer que la aplicación funcione en modo sin conexión"
            ),
            1
        ),
        Pregunta(
            "¿Qué componente del sistema operativo móvil se encarga de gestionar la memoria, las aplicaciones y los recursos del dispositivo?",
            listOf(
                "El núcleo o kernel del sistema operativo.",
                "La interfaz de usuario (UI).",
                "El motor de renderizado.",
                "La base de datos local de la aplicación."
            ),
            0
        ),
        Pregunta(
            "¿Qué tipo de aplicaciones móviles requieren un conjunto completo de permisos para acceder a funcionalidades como la cámara, ubicación, contactos, etc.?",
            listOf(
                "Las aplicaciones web.",
                "Las aplicaciones híbridas.",
                "Las aplicaciones nativas.",
                "Las Progressive Web Apps (PWA)."
            ),
            2
        ),
        Pregunta(
            "¿Cuál es el archivo principal que describe los componentes de una app Android?",
            listOf(
                "build.gradle",
                "AndroidManifest.xml",
                "MainActivity.kt",
                "strings.xml"
            ),
            1
        ),
        Pregunta(
            "¿Qué es una Activity en Android?",
            listOf(
                "Una base de datos local",
                "Una pantalla o interfaz que el usuario puede ver",
                "Un permiso de red",
                "Una herramienta para dibujar gráficos"
            ),
            1
        ),
        Pregunta(
            "¿Qué sistema de diseño de interfaz proporciona Google para Android?",
            listOf(
                "Material Design",
                "Apple UI",
                "Windows Metro",
                "Bootstrap"
            ),
            0
        ),
        Pregunta(
            "¿Qué componente se utiliza para almacenar datos localmente en Android?",
            listOf(
                "RecyclerView",
                "Room Database",
                "ConstraintLayout",
                "Toast"
            ),
            1
        ),
        Pregunta(
            "¿Cuál es una práctica recomendada en el diseño de interfaces móviles?",
            listOf(
                "Colocar todos los botones en la parte superior",
                "Evitar el uso de iconos",
                "Diseñar pensando en la experiencia del usuario (UX)",
                "Usar muchos colores brillantes y saturados"
            ),
            2
        ),
        Pregunta(
            "¿Cuál es la principal desventaja de las aplicaciones móviles nativas frente a las aplicaciones híbridas?",
            listOf(
                "Requieren más tiempo y esfuerzo de desarrollo para cada plataforma",
                "No pueden acceder a los recursos del dispositivo",
                "Son más lentas que las aplicaciones híbridas",
                "No pueden ser distribuidas en tiendas de aplicaciones"
            ),
            0
        ),
        Pregunta(
            "¿Cuál de los siguientes componentes del ciclo de vida de una Activity en Android es el más adecuado para liberar recursos pesados como conexiones de base de datos o listeners para evitar fugas de memoria?",
            listOf(
                "onCreate()",
                "onResume()",
                "onPause()",
                "onDestroy()"
            ),
            3
        )

    )
}