package com.creators.dec20_todolistclean_anton.di

import android.content.Context
import androidx.room.Room
import com.creators.dec20_todolistclean_anton.data.firebase.AuthRepositoryImpl
import com.creators.dec20_todolistclean_anton.data.local.TodoDao
import com.creators.dec20_todolistclean_anton.data.local.TodoDatabase
import com.creators.dec20_todolistclean_anton.data.repository.TodoRepositoryImpl
import com.creators.dec20_todolistclean_anton.domain.repository.TodoRepository
import com.creators.dec20_todolistclean_anton.domain.usecases.AddTodoUseCase
import com.creators.dec20_todolistclean_anton.domain.usecases.DeleteTodoUseCase
import com.creators.dec20_todolistclean_anton.domain.usecases.GetAllTodosUseCase
import com.creators.dec20_todolistclean_anton.domain.usecases.UpdateTodoUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepositoryImpl {
        return AuthRepositoryImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideTodoDatabase(
        @ApplicationContext context: Context
    ): TodoDatabase {
        return Room.databaseBuilder(
            context,
            TodoDatabase::class.java,
            "todo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(db: TodoDatabase): TodoDao = db.todoDao()

    @Provides
    @Singleton
    fun provideTodoRepository(dao: TodoDao): TodoRepository {
        return TodoRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideAddTodoUseCase(repository: TodoRepository): AddTodoUseCase {
        return AddTodoUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateTodoUseCase(repository: TodoRepository): UpdateTodoUseCase {
        return UpdateTodoUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteTodoUseCase(repository: TodoRepository): DeleteTodoUseCase {
        return DeleteTodoUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAllTodosUseCase(repository: TodoRepository): GetAllTodosUseCase {
        return GetAllTodosUseCase(repository)
    }
}