package com.example.randomtasks_workmanager.domain.repository

import com.example.randomtasks_workmanager.domain.event.TaskResult
import com.example.randomtasks_workmanager.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository{
    suspend fun getTask() : Flow<TaskResult<Task>>
}