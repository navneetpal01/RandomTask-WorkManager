package com.example.randomtasks_workmanager.data.remote

import com.example.randomtasks_workmanager.domain.model.Task
import retrofit2.http.GET

interface TaskApi{


    @GET("activity")
    suspend fun getTask() : Task

    companion object{
        const val BASE_URL = "https://www.boredapi.com/api/"
    }
}