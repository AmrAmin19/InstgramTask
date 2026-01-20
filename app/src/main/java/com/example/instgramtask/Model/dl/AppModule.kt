package com.example.instgramtask.Model.dl

import android.content.Context
import androidx.room.Room
import com.example.instgramtask.Model.LocalData.AppDatabase
import com.example.instgramtask.Model.LocalData.ILocal
import com.example.instgramtask.Model.LocalData.LocalData
import com.example.instgramtask.Model.LocalData.PostsDao
import com.example.instgramtask.Model.RemoteData.ImockRemote
import com.example.instgramtask.Model.RemoteData.MockRemoteData
import com.example.instgramtask.Model.Repo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "posts_db"
        ).build()

    @Provides
    fun providePostsDao(db: AppDatabase): PostsDao = db.postsDao()

    @Provides

    fun provideLocalData(dao: PostsDao): ILocal =
        LocalData(dao)

    @Provides

    fun provideRemoteData(): ImockRemote =
        MockRemoteData()

    @Provides

    fun provideRepo(
        remote: ImockRemote,
        local: ILocal
    ): Repo = Repo(remote, local)
}