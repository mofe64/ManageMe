package com.nubari.aking.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.nubari.aking.data.converters.Converters
import com.nubari.aking.data.model.Task

@Database(entities = [Task::class], version = 1)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    abstract val taskDao: TaskDao

    companion object {
        const val DATABASE_NAME = "tasks_db"
    }
}