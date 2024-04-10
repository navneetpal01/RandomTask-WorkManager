package com.example.randomtasks_workmanager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomtasks_workmanager.domain.event.TaskResult
import com.example.randomtasks_workmanager.domain.model.Task
import com.example.randomtasks_workmanager.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {
    private val _task: MutableStateFlow<Task?> = MutableStateFlow(null)
    val task = _task.asStateFlow()

    init {
        viewModelScope.launch {
            taskRepository.getTask().collectLatest {task ->
                when(task){
                    is TaskResult.Failure -> {

                    }
                    is TaskResult.Success -> {
                        task.data?.let { task ->
                            _task.update {
                                task
                            }
                        }
                    }
                }
            }
        }
    }


}