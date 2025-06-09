package com.ingeweek.ui.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.ingeweek.R
import com.ingeweek.ui.agenda.AgendaFragment
import com.ingeweek.ui.competencias.CompetenciasFragment
import com.ingeweek.ui.seminarios.SeminariosFragment
import com.ingeweek.ui.ubicaciones.UbicacionesFragment

object AppNavGraph {

    fun navigateTo(activity: FragmentActivity, destination: Destination) {
        val fragment: Fragment = when (destination) {
            Destination.AGENDA -> AgendaFragment()
            Destination.COMPETENCIAS -> CompetenciasFragment()
            Destination.SEMINARIOS -> SeminariosFragment()
            Destination.UBICACIONES -> UbicacionesFragment()
        }

        activity.supportFragmentManager.commit {
            replace(R.id.nav_host_fragment, fragment)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    enum class Destination {
        AGENDA,
        COMPETENCIAS,
        SEMINARIOS,
        UBICACIONES
    }
}
