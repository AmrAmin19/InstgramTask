package com.example.instgramtask.Model.LocalData

import com.example.instgramtask.Model.Post

interface ILocal {
    suspend fun getCachedPosts(): List<Post>
    suspend fun savePosts(posts: List<Post>)
}