package com.example.randomtasks_workmanager

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.randomtasks_workmanager.data.remote.TaskApi
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.math.log


@HiltWorker
class CustomWorker  @AssistedInject constructor(
   @Assisted private val taskApi : TaskApi,
   @Assisted context : Context,
   @Assisted workerParameters: WorkerParameters
): CoroutineWorker(context,workerParameters){
   @SuppressLint("RestrictedApi")
   override suspend fun doWork(): Result {
       runBlocking {

        delay(10000)
       }
       Log.d("server", "data fetched successfully")
        return Result.Success()
    }
}


/**
class CustomWorker(
    context : Context,
    workerParameters: WorkerParameters
): Worker(context,workerParameters){
    override fun doWork(): Result {
        println("Hello from Worker!")
        return Result.success()
    }
}
 **/
