package com.example.instgramtask.Model.RemoteData

import com.example.instgramtask.Model.ApiState
import com.example.instgramtask.Model.Post
import kotlinx.coroutines.flow.Flow

interface ImockRemote {
    fun getPosts(): Flow<ApiState<List<Post>>>
}