package com.creators.dec20_todolistclean_anton.domain.repository

import com.creators.dec20_todolistclean_anton.domain.model.TodoItem
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getAllTodos(): Flow<List<TodoItem>>
    suspend fun getTodoById(id: Int): TodoItem?
    suspend fun addTodo(todo: TodoItem)
    suspend fun updateTodo(todo: TodoItem)
    suspend fun deleteTodo(todo: TodoItem)
}