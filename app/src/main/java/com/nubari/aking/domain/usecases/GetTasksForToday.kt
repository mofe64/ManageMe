package com.nubari.aking.domain.usecases

import com.nubari.aking.data.model.Task
import com.nubari.aking.data.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasksForToday(
    private val repository: TaskRepository
) {
    operator fun invoke(): Flow<List<Task>> {
        return repository.getTodayTasks()
    }
}