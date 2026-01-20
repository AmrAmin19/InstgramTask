package com.example.instgramtask.Model.LocalData

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.instgramtask.Model.Post

@Database(entities = [Post::class], version = 1, exportSchema = false)
@TypeConverters(MediaConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao

//    companion object{
//        @Volatile
//        private var Instance : AppDatabase? = null
//
//        fun getInstance(context: Context) : AppDatabase{
//            return Instance ?: synchronized(this){
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,AppDatabase::class.java,"Posts_Database"
//                ).build()
//                Instance = instance
//                instance
//            }
//        }
//    }

}
