package com.ingeweek.ui.competencias

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ingeweek.databinding.FragmentCompetenciasBinding

class CompetenciasFragment : Fragment() {

    private var _binding: FragmentCompetenciasBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CompetenciasViewModel by viewModels()
    private lateinit var adapter: CompetenciasAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCompetenciasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = CompetenciasAdapter()
        binding.recyclerViewCompetencias.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewCompetencias.adapter = adapter

        viewModel.listaCompetencias.observe(viewLifecycleOwner) { competencias ->
            adapter.submitList(competencias)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
