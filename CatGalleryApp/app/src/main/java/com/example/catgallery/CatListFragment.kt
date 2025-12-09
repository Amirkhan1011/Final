package com.example.catgallery

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catgallery.state.UiState
import com.example.catgallery.viewModel.CatViewModel

class CatListFragment : Fragment(R.layout.fragment_cats) {

    private val viewModel: CatViewModel by viewModels()

    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var reloadButton: Button
    private lateinit var adapter: CatCardAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progress)
        recyclerView = view.findViewById(R.id.recyclerView)
        reloadButton = view.findViewById(R.id.buttonReload)

        adapter = CatCardAdapter()
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,   // <— ВАЖНО
            false
        )
        recyclerView.adapter = adapter

        reloadButton.setOnClickListener {
            viewModel.loadCats(5)
        }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    reloadButton.isEnabled = false
                }
                is UiState.Success -> {
                    progressBar.visibility = View.GONE
                    reloadButton.isEnabled = true
                    adapter.submitList(state.cats)
                }
                is UiState.Error -> {
                    progressBar.visibility = View.GONE
                    reloadButton.isEnabled = true
                    Toast.makeText(requireContext(), "Error: ${state.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        if (savedInstanceState == null) {
            viewModel.loadCats(5)
        }
    }
}

