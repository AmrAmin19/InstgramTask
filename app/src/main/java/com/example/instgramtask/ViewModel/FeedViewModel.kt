package com.example.instgramtask.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instgramtask.Model.ApiState
import com.example.instgramtask.Model.Post
import com.example.instgramtask.Model.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FeedViewModel @Inject constructor(
    private val repo: Repo
) : ViewModel() {

    private val _posts =
        MutableStateFlow<ApiState<List<Post>>>(ApiState.Loading)
    val posts = _posts.asStateFlow()

    init {
        loadPosts()
    }

    private fun loadPosts() {
        viewModelScope.launch {
            repo.getPosts().collect { state ->
                _posts.value = state
            }
        }
    }
}
