package com.nubari.aking.data.datasource

import androidx.room.*
import com.nubari.aking.data.model.Task
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface TaskDao {
    @Query("select * from task order by due_date desc")
    fun getAllTasks(): Flow<List<Task>>

    @Query("select * from task where completed = 1 order by due_date desc")
    fun getAllCompletedTasks(): Flow<List<Task>>

    @Query("select * from task where completed = 0 order by due_date desc")
    fun getAllUncompletedTasks(): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("select * from task where id =:id")
    suspend fun getTask(id: String): Task?

    @Query("select * from task where due_date =:date")
    fun getTaskByDate(date: Date): Flow<List<Task>>
}