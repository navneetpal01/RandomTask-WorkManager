package com.example.randomtasks_workmanager.data.repository

import com.example.randomtasks_workmanager.data.remote.TaskApi
import com.example.randomtasks_workmanager.domain.event.TaskResult
import com.example.randomtasks_workmanager.domain.model.Task
import com.example.randomtasks_workmanager.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskApi: TaskApi
): TaskRepository {
    override suspend fun getTask(): Flow<TaskResult<Task>> {
        return flow {
            val task = try {
                taskApi.getTask()
            }catch (e : Exception){
                emit(TaskResult.Failure(message = "Check your Internet Connection"))
                return@flow
            }
            emit(TaskResult.Success(data = task))
        }
    }

}