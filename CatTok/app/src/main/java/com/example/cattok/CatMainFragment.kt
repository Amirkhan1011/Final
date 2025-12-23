package com.example.cattok

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.cattok.state.VideoUiState
import com.example.cattok.viewModel.CatVideoViewModel

class CatMainFragment : Fragment(R.layout.fragment_cat_main) {
    private val videoViewModel: CatVideoViewModel by viewModels()
    private lateinit var pagerAdapter: CatVideoPagerAdapter
    private lateinit var viewPager: ViewPager2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = view.findViewById(R.id.videoPager)
        pagerAdapter = CatVideoPagerAdapter()
        viewPager.adapter = pagerAdapter
        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL



        videoViewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is VideoUiState.Loading -> {
                }
                is VideoUiState.Success -> {
                    pagerAdapter.submitList(state.videos)
                }
                is VideoUiState.Error -> {
                    context?.let { ctx ->
                        Toast.makeText(ctx, state.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
