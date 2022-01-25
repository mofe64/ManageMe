package com.nubari.aking.presentation.home.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nubari.aking.domain.usecases.TaskUseCases
import com.nubari.aking.presentation.home.events.DayTaskEvent
import com.nubari.aking.presentation.home.state.TasksState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DayTaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases
) : ViewModel() {
    private val _state = MutableLiveData(TasksState())
    val state: LiveData<TasksState>
        get() = _state

    private var getTaskJob: Job? = null

    init {
        getTasksForToday()
    }

    fun createEvent(eventDay: DayTaskEvent) {
        onEvent(eventDay)
    }

    private fun onEvent(event: DayTaskEvent) {
        when (event) {
            is DayTaskEvent.DeleteTask -> {
                viewModelScope.launch {
                    taskUseCases.deleteTask(event.task)
                }
            }
            is DayTaskEvent.EditTask -> {
                viewModelScope.launch {
                    delay(3000)
                }
            }
            is DayTaskEvent.GetTasksForToday -> {
                getTasksForToday()
            }

        }
    }

    private fun getTasksForToday() {
        //cancel old job if still running
        getTaskJob?.cancel()
        getTaskJob = taskUseCases.getTasksForToday()
            .onEach { tasks ->
                _state.value = state.value?.copy(
                    tasks = tasks
                )
            }
            .launchIn(viewModelScope)

    }

}