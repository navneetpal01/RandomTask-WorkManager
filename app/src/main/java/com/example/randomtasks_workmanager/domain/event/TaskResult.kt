package com.example.randomtasks_workmanager.domain.event

sealed class TaskResult<out T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?) : TaskResult<T>(data = data)
    class Failure(message: String) : TaskResult<Nothing>()
}