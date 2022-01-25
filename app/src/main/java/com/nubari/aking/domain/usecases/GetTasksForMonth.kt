package com.nubari.aking.domain.usecases

import com.nubari.aking.data.model.Task
import com.nubari.aking.data.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasksForMonth(
    private val repository: TaskRepository
) {
    //Todo("Refactor this to use actual month")
    operator fun invoke(): Flow<List<Task>> {
        return repository.getTasks()
    }
}