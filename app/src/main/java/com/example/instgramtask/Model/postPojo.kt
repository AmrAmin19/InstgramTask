package com.example.instgramtask.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class Post(
    @PrimaryKey val id: String,
    val username: String,
    val caption: String,
    val media: List<Media>,
    val timestamp: Long
)