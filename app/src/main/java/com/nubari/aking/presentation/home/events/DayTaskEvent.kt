package com.nubari.aking.presentation.home.events

import com.nubari.aking.data.model.Task

sealed class DayTaskEvent {
    data class EditTask(val task: Task) : DayTaskEvent()
    data class DeleteTask(val task: Task) : DayTaskEvent()
    object GetTasksForToday : DayTaskEvent()

}
