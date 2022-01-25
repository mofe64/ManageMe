package com.nubari.aking.data.repository

import com.nubari.aking.data.model.Task
import kotlinx.coroutines.flow.Flow
import java.util.*

interface TaskRepository {
    fun getTasks(): Flow<List<Task>>
    fun getCompletedTasks(): Flow<List<Task>>
    fun getUncompletedTasks(): Flow<List<Task>>
    suspend fun saveTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun getTaskById(id: String): Task?
    fun getTaskByDate(date: Date): Flow<List<Task>>
    fun getTodayTasks(): Flow<List<Task>>

}