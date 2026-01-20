package com.example.instgramtask.ViewModel

import com.example.instgramtask.Model.ApiState
import com.example.instgramtask.Model.FakeRepo
import com.example.instgramtask.Model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FeedViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private lateinit var fakeRepo: FakeRepo
    private lateinit var viewModel: FeedViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        fakeRepo = FakeRepo()
        viewModel = FeedViewModel(fakeRepo)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when repo emits Loading, viewModel emits Loading`() = runTest {
        fakeRepo.emit(ApiState.Loading)

        viewModel.loadPosts()
        advanceUntilIdle()

        val result = viewModel.posts.first()

        assert(result is ApiState.Loading)
    }

    @Test
    fun `when repo emits Success, viewModel emits Success`() = runTest {
        val posts = listOf(
            Post(
                id = "1",
                username = "test",
                caption = "caption",
                media = emptyList(),
                timestamp = 0L
            )
        )

        fakeRepo.emit(ApiState.Success(posts))

        viewModel.loadPosts()
        advanceUntilIdle()

        val result = viewModel.posts.first()

        assert(result is ApiState.Success)
        assert((result as ApiState.Success).data == posts)
    }
}
