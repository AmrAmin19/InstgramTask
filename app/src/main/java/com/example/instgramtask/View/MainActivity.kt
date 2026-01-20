package com.example.instgramtask.View

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instgramtask.Model.ApiState
import com.example.instgramtask.R
import com.example.instgramtask.ViewModel.FeedViewModel
import com.example.instgramtask.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {



    private lateinit var binding: ActivityMainBinding
    private lateinit var feedAdapter: FeedAdapter

    private val viewModel: FeedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeFeed()
    }

    private fun setupRecyclerView() {
        feedAdapter = FeedAdapter()

        binding.rvFeed.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = feedAdapter
            setHasFixedSize(true)
        }
    }

    private fun observeFeed() {
        lifecycleScope.launch {
            viewModel.posts.collect { state ->
                when (state) {
                    is ApiState.Loading -> {
                        binding.shimmerLayout.startShimmer()
                        binding.shimmerLayout.visibility = View.VISIBLE
                        binding.rvFeed.visibility = View.GONE
                    }

                    is ApiState.Success -> {
                        binding.shimmerLayout.stopShimmer()
                        binding.shimmerLayout.visibility = View.GONE
                        binding.rvFeed.visibility = View.VISIBLE
                        feedAdapter.submitList(state.data)
                    }

                    is ApiState.Error -> {
                        binding.shimmerLayout.stopShimmer()
                        Toast.makeText(this@MainActivity, state.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
