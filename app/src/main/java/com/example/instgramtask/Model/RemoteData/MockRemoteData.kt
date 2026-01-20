package com.example.instgramtask.Model.RemoteData
import com.example.instgramtask.Model.ApiState
import com.example.instgramtask.Model.Media
import com.example.instgramtask.Model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MockRemoteData @Inject constructor() : ImockRemote {

    override fun getPosts(): Flow<ApiState<List<Post>>> = flow {
        emit(ApiState.Loading)

//        delay(800)

        emit(ApiState.Success(mockPosts()))
    }

    private fun mockPosts(): List<Post> =
        listOf(
            Post(
                id = "1",
                username = "zyad_dev",
                caption = "Hello feed 👋",
                media = listOf(
                    Media.Image("android.resource://com.example.instgramtask/drawable/img1")
                ),
                timestamp = System.currentTimeMillis()
            ),
            Post(
                id = "2",
                username = "android_daily",
                caption = "Video time 🎥",
                media = listOf(
                    Media.Video(
                        "android.resource://com.example.instgramtask/raw/video1",
                        15000
                    )
                ),
                timestamp = System.currentTimeMillis()
            )
        )
}