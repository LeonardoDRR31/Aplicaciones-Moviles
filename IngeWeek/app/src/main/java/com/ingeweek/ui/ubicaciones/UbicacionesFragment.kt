package com.ingeweek.ui.ubicaciones

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ingeweek.databinding.FragmentUbicacionesBinding

class UbicacionesFragment : Fragment() {

    private var _binding: FragmentUbicacionesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UbicacionesViewModel by viewModels()
    private lateinit var adapter: UbicacionesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentUbicacionesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = UbicacionesAdapter()
        binding.recyclerViewUbicaciones.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewUbicaciones.adapter = adapter

        viewModel.listaUbicaciones.observe(viewLifecycleOwner) { ubicaciones ->
            adapter.submitList(ubicaciones)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
