package com.nubari.aking.data.repository

import com.nubari.aking.data.datasource.TaskDao
import com.nubari.aking.data.model.Task
import kotlinx.coroutines.flow.Flow
import java.util.*

class TaskRepositoryImpl(
    private val taskDao: TaskDao
) : TaskRepository {
    override fun getTasks(): Flow<List<Task>> {
        return taskDao.getAllTasks()
    }

    override fun getCompletedTasks(): Flow<List<Task>> {
        return taskDao.getAllCompletedTasks()
    }

    override fun getUncompletedTasks(): Flow<List<Task>> {
        return taskDao.getAllUncompletedTasks()
    }

    override suspend fun saveTask(task: Task) {
        return taskDao.save(task)
    }

    override suspend fun deleteTask(task: Task) {
        return taskDao.delete(task)
    }

    override suspend fun getTaskById(id: String): Task? {
        return taskDao.getTask(id)
    }

    override fun getTaskByDate(date: Date): Flow<List<Task>> {
        return taskDao.getTaskByDate(date)
    }

    override fun getTodayTasks(): Flow<List<Task>> {
        val today = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault()).time
        return taskDao.getTaskByDate(today)
    }
}