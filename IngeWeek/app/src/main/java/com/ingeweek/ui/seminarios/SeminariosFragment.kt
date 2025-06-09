package com.ingeweek.ui.seminarios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ingeweek.databinding.FragmentSeminariosBinding

class SeminariosFragment : Fragment() {

    private var _binding: FragmentSeminariosBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SeminariosViewModel by viewModels()
    private lateinit var adapter: SeminariosAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSeminariosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = SeminariosAdapter()
        binding.recyclerViewSeminarios.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewSeminarios.adapter = adapter

        viewModel.listaSeminarios.observe(viewLifecycleOwner) { seminarios ->
            adapter.submitList(seminarios)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
