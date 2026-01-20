package com.example.instgramtask.Model.LocalData

import com.example.instgramtask.Model.Post
import javax.inject.Inject


class LocalData @Inject constructor(
    private val dao: PostsDao
) : ILocal {

    override suspend fun getCachedPosts(): List<Post> {
        return dao.getPosts()
    }

    override suspend fun savePosts(posts: List<Post>) {
        dao.insertPosts(posts)
    }
}