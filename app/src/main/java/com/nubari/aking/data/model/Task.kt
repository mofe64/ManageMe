package com.nubari.aking.data.model

import java.time.LocalDateTime
import java.util.*

data class Task(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    var completed: Boolean,
    val dueDate: String
)
