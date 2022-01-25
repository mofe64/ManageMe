package com.nubari.aking.di.core

import android.app.Application
import androidx.room.Room
import com.nubari.aking.data.datasource.Database
import com.nubari.aking.data.repository.TaskRepository
import com.nubari.aking.data.repository.TaskRepositoryImpl
import com.nubari.aking.domain.usecases.AddTask
import com.nubari.aking.domain.usecases.GetTasks
import com.nubari.aking.domain.usecases.TaskUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTaskDatabase(app: Application): Database {
        return Room.databaseBuilder(
            app,
            Database::class.java,
            Database.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(db: Database): TaskRepository {
        return TaskRepositoryImpl(db.taskDao)
    }

    @Provides
    @Singleton
    fun provideTaskUseCases(repository: TaskRepository): TaskUseCases {
        return TaskUseCases(
            getTasks = GetTasks(repository),
            addTask = AddTask(repository)
        )
    }


}