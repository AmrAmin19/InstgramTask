package com.example.instgramtask.View

import android.graphics.Rect
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
import androidx.recyclerview.widget.RecyclerView
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

        binding.swipeRefresh.setOnRefreshListener {
            binding.rvFeed.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            viewModel.refresh()
        }

        setupAutoPlay()



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
                        if (!binding.swipeRefresh.isRefreshing) {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.rvFeed.visibility = View.GONE
                        }
                    }

                    is ApiState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.swipeRefresh.isRefreshing = false
                        binding.rvFeed.visibility = View.VISIBLE
                        feedAdapter.submitList(state.data)
                    }

                    is ApiState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.swipeRefresh.isRefreshing = false
                        Toast.makeText(this@MainActivity, state.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


    private fun setupAutoPlay() {
        binding.rvFeed.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val first = layoutManager.findFirstVisibleItemPosition()
                val last = layoutManager.findLastVisibleItemPosition()

                for (i in first..last) {
                    val holder = recyclerView.findViewHolderForAdapterPosition(i)

                    if (holder is FeedAdapter.VideoViewHolder) {
                        if (isFullyVisible(holder.itemView)) {
                            holder.play()
                        } else {
                            holder.pause()
                        }
                    }
                }
            }
        })
    }

    private fun isFullyVisible(view: View): Boolean {
        val rect = Rect()
        view.getGlobalVisibleRect(rect)
        return rect.height() >= view.height
    }



}
