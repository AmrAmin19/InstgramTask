package com.example.instgramtask.Model.LocalData

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.instgramtask.Model.Post

@Dao
interface PostsDao {

    @Query("SELECT * FROM posts")
    suspend fun getPosts(): List<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<Post>)
}