package com.nubari.aking.domain.usecases

data class TaskUseCases(
    val getTasks: GetTasks,
    val addTask: AddTask,
    val deleteTask: DeleteTask,
    val getTasksForToday: GetTasksForToday,
    val getTasksForMonth: GetTasksForMonth,
)