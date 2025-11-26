package com.example.catgallery

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catgallery.state.UiState
import com.example.catgallery.viewModel.CatViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: CatViewModel by viewModels()

    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var reloadButton: Button
    private lateinit var adapter: CatCardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progress)
        recyclerView = findViewById(R.id.recyclerView)
        reloadButton = findViewById(R.id.buttonReload)

        adapter = CatCardAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        reloadButton.setOnClickListener {
            viewModel.loadCats(10)
        }

        viewModel.state.observe(this) { state ->
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
                    Toast.makeText(this, "Error: ${state.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // initial load
        viewModel.loadCats(10)
    }
}
