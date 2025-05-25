package com.example.clinicarobles.data

import com.example.clinicarobles.R
import com.example.clinicarobles.model.Medico

object MedicosData {
    val medicosPorEspecialidad = mapOf(
        "Traumatología" to listOf(
            Medico("Dr. Juan Torres", "juan.torres@clinic.com", "900100100", R.drawable.doctor1),
            Medico("Dra. Elena Ruiz", "elena.ruiz@clinic.com", "900100101", R.drawable.doctora1)
        ),
        "Cardiología" to listOf(
            Medico("Dr. Pablo Márquez", "pablo.marquez@clinic.com", "900100102", R.drawable.doctor2),
            Medico("Dra. Laura Salinas", "laura.salinas@clinic.com", "900100103", R.drawable.doctora2)
        ),
        "Medicina Física" to listOf(
            Medico("Dr. Hugo López", "hugo.lopez@clinic.com", "900100104", R.drawable.doctor1),
            Medico("Dra. Andrea Medina", "andrea.medina@clinic.com", "900100105", R.drawable.doctora2)
        ),
        "Cirugía Cardiovascular" to listOf(
            Medico("Dr. Diego Herrera", "diego.herrera@clinic.com", "900100106", R.drawable.doctor1),
            Medico("Dra. Carla Bravo", "carla.bravo@clinic.com", "900100107", R.drawable.doctora2)
        ),
        "Cirugía General" to listOf(
            Medico("Dr. Mario Díaz", "mario.diaz@clinic.com", "900100108", R.drawable.doctor1),
            Medico("Dra. Silvia Ortega", "silvia.ortega@clinic.com", "900100109", R.drawable.doctora2)
        ),
        "Dermatología" to listOf(
            Medico("Dr. Carlos Pérez", "carlos.perez@clinic.com", "987654323", R.drawable.doctor1),
            Medico("Dra. Eliza Díaz", "eliza.diaz@clinic.com", "987654324", R.drawable.doctora2)
        ),
        "Endocrinología" to listOf(
            Medico("Dr. Tomás Aguilar", "tomas.aguilar@clinic.com", "900100110", R.drawable.doctor1),
            Medico("Dra. Beatriz Ramos", "beatriz.ramos@clinic.com", "900100111", R.drawable.doctora2)
        ),
        "Gastroenterología" to listOf(
            Medico("Dr. Javier Silva", "javier.silva@clinic.com", "900100112", R.drawable.doctor1),
            Medico("Dra. Inés Palma", "ines.palma@clinic.com", "900100113", R.drawable.doctora2)
        ),
        "Ginecología" to listOf(
            Medico("Dr. Alfredo Ríos", "alfredo.rios@clinic.com", "900100114", R.drawable.doctor1),
            Medico("Dra. Cecilia Vera", "cecilia.vera@clinic.com", "900100115", R.drawable.doctora2)
        ),
        "Medicina General" to listOf(
            Medico("Dr. Luis Mena", "luis.mena@clinic.com", "900100116", R.drawable.doctor1),
            Medico("Dra. Nancy Torres", "nancy.torres@clinic.com", "900100117", R.drawable.doctora2)
        ),
        "Medicina Interna" to listOf(
            Medico("Dr. Arturo Peña", "arturo.pena@clinic.com", "900100118", R.drawable.doctor1),
            Medico("Dra. Sandra Núñez", "sandra.nunez@clinic.com", "900100119", R.drawable.doctora2)
        ),
        "Neumología" to listOf(
            Medico("Dr. Óscar Romero", "oscar.romero@clinic.com", "900100120", R.drawable.doctor1),
            Medico("Dra. Paula Márquez", "paula.marquez@clinic.com", "900100121", R.drawable.doctora2)
        ),
        "Neurocirugía" to listOf(
            Medico("Dr. Rafael Ávila", "rafael.avila@clinic.com", "900100122", R.drawable.doctor1),
            Medico("Dra. Lorena Vives", "lorena.vives@clinic.com", "900100123", R.drawable.doctora2)
        ),
        "Neurología" to listOf(
            Medico("Dr. Ernesto Ruiz", "ernesto.ruiz@clinic.com", "900100124", R.drawable.doctor1),
            Medico("Dra. Gabriela Ríos", "gabriela.rios@clinic.com", "900100125", R.drawable.doctora2)
        ),
        "Nutrición" to listOf(
            Medico("Dr. Mateo León", "mateo.leon@clinic.com", "900100126", R.drawable.doctor1),
            Medico("Dra. Julia Fuentes", "julia.fuentes@clinic.com", "900100127", R.drawable.doctora2)
        ),
        "Odontología" to listOf(
            Medico("Dr. Sergio Paz", "sergio.paz@clinic.com", "900100128", R.drawable.doctor1),
            Medico("Dra. Mariana Mora", "mariana.mora@clinic.com", "900100129", R.drawable.doctora2)
        ),
        "Cirugía Maxilofacial" to listOf(
            Medico("Dr. Alan Campos", "alan.campos@clinic.com", "900100130", R.drawable.doctor1),
            Medico("Dra. Teresa Nieto", "teresa.nieto@clinic.com", "900100131", R.drawable.doctora2)
        ),
        "Otorrinolaringología" to listOf(
            Medico("Dr. Daniel Peralta", "daniel.peralta@clinic.com", "900100132", R.drawable.doctor1),
            Medico("Dra. Verónica Galeano", "veronica.galeano@clinic.com", "900100133", R.drawable.doctora2)
        ),
        "Oftalmología" to listOf(
            Medico("Dr. Julián Arias", "julian.arias@clinic.com", "900100134", R.drawable.doctor1),
            Medico("Dra. Mónica Salvatierra", "monica.salvatierra@clinic.com", "900100135", R.drawable.doctora2)
        ),
        "Pediatría" to listOf(
            Medico("Dra. María Gómez", "maria.gomez@clinic.com", "987654321", R.drawable.doctora1),
            Medico("Dr. Luis Salas", "luis.salas@clinic.com", "987654322", R.drawable.doctora2)
        ),
        "Psicología" to listOf(
            Medico("Dr. Cristian Fajardo", "cristian.fajardo@clinic.com", "900100136", R.drawable.doctor1),
            Medico("Dra. Valeria Reinoso", "valeria.reinoso@clinic.com", "900100137", R.drawable.doctora2)
        ),
        "Psiquiatría" to listOf(
            Medico("Dr. Sergio Varela", "sergio.varela@clinic.com", "900100138", R.drawable.doctor1),
            Medico("Dra. Ángela Cabrera", "angela.cabrera@clinic.com", "900100139", R.drawable.doctor1)
        ),
        "Reumatología" to listOf(
            Medico("Dr. Jaime Paredes", "jaime.paredes@clinic.com", "900100140", R.drawable.doctor1),
            Medico("Dra. Rocío Espejo", "rocio.espejo@clinic.com", "900100141", R.drawable.doctora2)
        ),
        "Urología" to listOf(
            Medico("Dr. Víctor Muñoz", "victor.munoz@clinic.com", "900100142", R.drawable.doctor1),
            Medico("Dra. Marta Villalba", "marta.villalba@clinic.com", "900100143", R.drawable.doctora2)
        )
    )
}
