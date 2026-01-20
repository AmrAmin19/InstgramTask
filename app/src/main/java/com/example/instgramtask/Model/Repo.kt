package com.example.instgramtask.Model

import com.example.instgramtask.Model.LocalData.ILocal
import com.example.instgramtask.Model.RemoteData.ImockRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repo @Inject constructor(
    private val remote: ImockRemote,
    private val local: ILocal
) {



    fun getPosts(): Flow<ApiState<List<Post>>> = flow {
        emit(ApiState.Loading)

        val cached = local.getCachedPosts()
        if (cached.isNotEmpty()) {
            emit(ApiState.Success(cached))
            return@flow
        }

        remote.getPosts().collect { state ->
            if (state is ApiState.Success) {
                local.savePosts(state.data)
            }
            emit(state)
        }
    }


}