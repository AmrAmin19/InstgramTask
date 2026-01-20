package com.example.instgramtask.Model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepo : Irepo {

    private var state: ApiState<List<Post>> = ApiState.Loading

    fun emit(state: ApiState<List<Post>>) {
        this.state = state
    }

    override fun getPosts(): Flow<ApiState<List<Post>>> = flow {
        emit(state)
    }
}