package com.example.instgramtask.View

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.instgramtask.Model.ApiState
import com.example.instgramtask.R
import com.example.instgramtask.ViewModel.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: FeedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observePosts()
    }

    private fun observePosts() {
        lifecycleScope.launch {
            viewModel.posts.collect { state ->
                when (state) {

                    is ApiState.Loading -> {
                        Log.d("FEED_TEST", "Loading posts...")
                    }

                    is ApiState.Success -> {
                        Log.d(
                            "FEED_TEST",
                            "Posts arrived: ${state.data.size}"
                        )

                        state.data.forEach {
                            Log.d(
                                "FEED_TEST",
                                "Post: ${it.username} - ${it.caption}"
                            )
                        }
                    }

                    is ApiState.Error -> {
                        Log.e("FEED_TEST", state.message)
                    }
                }
            }
        }
    }
}
