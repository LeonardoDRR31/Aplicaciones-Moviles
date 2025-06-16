package com.example.diarioelperuano

object MenuData {
    val items = listOf(
        MenuItem.Simple("Inicio", "https://elperuano.pe/"),
        MenuItem.Simple("Derecho", "https://elperuano.pe/derecho"),
        MenuItem.Simple("Economía", "https://elperuano.pe/economia"),
        MenuItem.WithSubItems(
            "Actualidad", listOf(
                MenuItem.Simple("Política", "https://elperuano.pe/politica"),
                MenuItem.Simple("País", "https://elperuano.pe/pais"),
                MenuItem.Simple("Mundo", "https://elperuano.pe/mundo"),
                MenuItem.Simple("Deporte", "https://elperuano.pe/deporte"),
                MenuItem.Simple("Cultural", "https://elperuano.pe/cultural")
            )
        ),
        MenuItem.WithSubItems(
            "Opinión", listOf(
                MenuItem.Simple("Editorial", "https://elperuano.pe/editorial")
            )
        ),
        MenuItem.WithSubItems(
            "Especiales", listOf(
                MenuItem.Simple("Central", "https://elperuano.pe/central"),
                MenuItem.Simple("Liderazgo", "https://elperuano.pe/Liderazgo"),
                MenuItem.Simple("Convivir", "https://elperuano.pe/convivir"),
                MenuItem.Simple("Convocación", "https://elperuano.pe/convocacion"),
                MenuItem.Simple("En confianza", "https://elperuano.pe/en-confianza"),
                MenuItem.Simple("Ciencia y tecnología", "https://elperuano.pe/ciencia-tecnologia"),
                MenuItem.Simple("Ozio", "https://elperuano.pe/ozio")
            )
        ),
        MenuItem.WithSubItems(
            "Suplementos", listOf(
                MenuItem.Simple("Ekonómica", "https://elperuano.pe/suplemento/economika"),
                MenuItem.Simple("La Crónica Universitaria", "https://elperuano.pe/suplemento/la-cronica-universitaria"),
                MenuItem.Simple("Lo nuestro", "https://elperuano.pe/suplemento/lo-nuestro"),
                MenuItem.Simple("Variedades", "https://elperuano.pe/variedades"),
                MenuItem.Simple("Economía y Derecho", "https://elperuano.pe/economia-derecho")
            )
        ),
        MenuItem.Simple("Museo", "https://museograficovirtual.editoraperu.com.pe/")
    )
}
