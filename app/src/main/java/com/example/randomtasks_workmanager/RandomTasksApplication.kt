package com.example.randomtasks_workmanager

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.randomtasks_workmanager.data.remote.TaskApi
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class RandomTasksApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: CustomWorkerFactory
    override val workManagerConfiguration: Configuration
        get() {
            return Configuration.Builder()
                .setMinimumLoggingLevel(Log.DEBUG)
                .setWorkerFactory(workerFactory)
                .build()
        }
    //TODO: Add the provider in the Manifest
    //When we want to add our own configuration we have to remove the the default initializer
}

class CustomWorkerFactory @Inject constructor(private val taskApi: TaskApi) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return CustomWorker(taskApi, appContext, workerParameters)
    }

}