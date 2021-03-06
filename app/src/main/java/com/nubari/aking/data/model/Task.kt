package com.nubari.aking.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Task(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "completed")
    var completed: Boolean,
    @ColumnInfo(name = "due_date", defaultValue = "(strftime('%s', 'now', 'localtime'))")
    val dueDate: Date? = null
)
