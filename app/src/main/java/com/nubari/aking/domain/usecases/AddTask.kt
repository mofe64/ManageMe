package com.nubari.aking.domain.usecases

import com.nubari.aking.data.model.Task
import com.nubari.aking.data.repository.TaskRepository
import com.nubari.aking.domain.exceptions.TaskException
import kotlin.jvm.Throws

class AddTask(
    private val taskRepository: TaskRepository
) {

    @Throws(TaskException::class)
    suspend operator fun invoke(task: Task) {
        if (task.title.isBlank()) throw TaskException("Task title cannot be blank")
        taskRepository.saveTask(task)
    }
}