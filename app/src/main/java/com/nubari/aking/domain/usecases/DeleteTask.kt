package com.nubari.aking.domain.usecases

import com.nubari.aking.data.model.Task
import com.nubari.aking.data.repository.TaskRepository

class DeleteTask(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(task: Task) {
        repository.deleteTask(task)
    }
}