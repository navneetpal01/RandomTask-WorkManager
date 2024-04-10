package com.example.randomtasks_workmanager.di

import com.example.randomtasks_workmanager.data.remote.TaskApi
import com.example.randomtasks_workmanager.data.remote.TaskApi.Companion.BASE_URL
import com.example.randomtasks_workmanager.data.repository.TaskRepositoryImpl
import com.example.randomtasks_workmanager.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Singleton
    @Provides
    fun providesTaskApi() : TaskApi{
        val httpLoggingInterceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client : OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(TaskApi::class.java)
    }

    @Singleton
    @Provides
    fun providesTaskRepository(taskApi: TaskApi) : TaskRepository{
        return TaskRepositoryImpl(taskApi)
    }
}