package com.example.instgramtask.Model

import kotlinx.coroutines.flow.Flow

interface Irepo {
    fun getPosts(): Flow<ApiState<List<Post>>>
}