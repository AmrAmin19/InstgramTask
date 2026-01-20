package com.example.instgramtask.Model.RemoteData
import com.example.instgramtask.Model.ApiState
import com.example.instgramtask.Model.Media
import com.example.instgramtask.Model.MediaType
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
                username = "Amr",
                caption = "Hello feed 👋",
                media = listOf(
                    Media(
                        type = MediaType.IMAGE,
                        uri = "android.resource://com.example.instgramtask/drawable/i1"
                    )
                ),
                timestamp = System.currentTimeMillis()
            ),
            Post(
                id = "2",
                username = "android_daily",
                caption = "Video time 🎥",
                media = listOf(
                    Media(
                        type = MediaType.VIDEO,
                        uri = "android.resource://com.example.instgramtask/raw/v2",
                        durationMs = 15000
                    )
                ),
                timestamp = System.currentTimeMillis()
            ),    Post(
                id = "3",
                username = "Amr_dev",
                caption = "Hello feed 👋",
                media = listOf(
                    Media(
                        type = MediaType.IMAGE,
                        uri = "android.resource://com.example.instgramtask/drawable/i2"
                    )
                ),
                timestamp = System.currentTimeMillis()
            ),
            Post(
                id = "4",
                username = "android_daily",
                caption = "Video time 🎥",
                media = listOf(
                    Media(
                        type = MediaType.VIDEO,
                        uri = "android.resource://com.example.instgramtask/raw/v3",
                        durationMs = 15000
                    )
                ),
                timestamp = System.currentTimeMillis()
            ),    Post(
                id = "5",
                username = "Amr",
                caption = "Hello feed 👋",
                media = listOf(
                    Media(
                        type = MediaType.IMAGE,
                        uri = "android.resource://com.example.instgramtask/drawable/i3"
                    )
                ),
                timestamp = System.currentTimeMillis()
            ),
            Post(
                id = "6",
                username = "android_daily",
                caption = "Video time 🎥",
                media = listOf(
                    Media(
                        type = MediaType.VIDEO,
                        uri = "android.resource://com.example.instgramtask/raw/v4",
                        durationMs = 15000
                    )
                ),
                timestamp = System.currentTimeMillis()
            ),
            Post(
                id = "7",
                username = "Amr",
                caption = "Hello feed 👋",
                media = listOf(
                    Media(
                        type = MediaType.IMAGE,
                        uri = "android.resource://com.example.instgramtask/drawable/i1"
                    )
                ),
                timestamp = System.currentTimeMillis()
            ),
            Post(
                id = "8",
                username = "android_daily",
                caption = "Video time 🎥",
                media = listOf(
                    Media(
                        type = MediaType.VIDEO,
                        uri = "android.resource://com.example.instgramtask/raw/v5",
                        durationMs = 15000
                    )
                ),
                timestamp = System.currentTimeMillis()
            ),    Post(
                id = "9",
                username = "Amr_dev",
                caption = "Hello feed 👋",
                media = listOf(
                    Media(
                        type = MediaType.IMAGE,
                        uri = "android.resource://com.example.instgramtask/drawable/i2"
                    )
                ),
                timestamp = System.currentTimeMillis()
            ),
            Post(
                id = "10",
                username = "android_daily",
                caption = "Video time 🎥",
                media = listOf(
                    Media(
                        type = MediaType.VIDEO,
                        uri = "android.resource://com.example.instgramtask/raw/v2",
                        durationMs = 15000
                    )
                ),
                timestamp = System.currentTimeMillis()
            ),    Post(
                id = "11",
                username = "Amr",
                caption = "Hello feed 👋",
                media = listOf(
                    Media(
                        type = MediaType.IMAGE,
                        uri = "android.resource://com.example.instgramtask/drawable/i3"
                    )
                ),
                timestamp = System.currentTimeMillis()
            ),
            Post(
                id = "12",
                username = "android_daily",
                caption = "Video time 🎥",
                media = listOf(
                    Media(
                        type = MediaType.VIDEO,
                        uri = "android.resource://com.example.instgramtask/raw/v4",
                        durationMs = 15000
                    )
                ),
                timestamp = System.currentTimeMillis()
            )

        )

}