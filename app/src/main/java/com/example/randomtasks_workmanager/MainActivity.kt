package com.example.randomtasks_workmanager

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.AndroidEntryPoint
import java.time.Duration


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
//        val viewModel by viewModels<MainViewModel>()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            )
        )
        super.onCreate(savedInstanceState)
        val workRequest = OneTimeWorkRequestBuilder<CustomWorker>()
            //will start after 10 seconds as our function will start
            .setInitialDelay(Duration.ofSeconds(20))
            //If our work will fail this is our retry policy
            //If will set it to exponential it means it will rerun after 15 sec for the first time and 30 sec after the second time and 60 sec for the third time
            .setBackoffCriteria(
                backoffPolicy = BackoffPolicy.LINEAR,
                duration = Duration.ofSeconds(15)
            )
            .build()
        WorkManager.getInstance(applicationContext).enqueue(workRequest)
        setContent {
//            val task = viewModel.task.collectAsState().value
//            task?.let { task ->
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize(),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                ){
//                    Text(
//                        text = task.activity,
//                        fontSize = 30.sp
//                    )
//                }
//            }
        }
    }
}
