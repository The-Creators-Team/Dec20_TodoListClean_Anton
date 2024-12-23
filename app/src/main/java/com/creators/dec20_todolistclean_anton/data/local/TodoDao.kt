package com.creators.dec20_todolistclean_anton.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.creators.dec20_todolistclean_anton.data.local.models.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos")
    fun getAllTodos(): Flow<List<TodoEntity>>

    @Query("SELECT * FROM todos WHERE id = :id")
    fun getTodoById(id: Int): TodoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTodo(todo: TodoEntity)

    @Update
    fun updateTodo(todo: TodoEntity)

    @Delete
    fun deleteTodo(todo: TodoEntity)
}