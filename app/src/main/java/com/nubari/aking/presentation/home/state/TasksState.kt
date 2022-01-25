package com.nubari.aking.presentation.home.state

import com.nubari.aking.data.model.Task

data class TasksState(
    val tasks: List<Task> = emptyList()
)
