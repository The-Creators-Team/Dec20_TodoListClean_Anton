package com.creators.dec20_todolistclean_anton.data.repository

import com.creators.dec20_todolistclean_anton.data.local.TodoDao
import com.creators.dec20_todolistclean_anton.data.local.models.TodoEntity
import com.creators.dec20_todolistclean_anton.domain.model.TodoItem
import com.creators.dec20_todolistclean_anton.domain.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class TodoRepositoryImpl(
    private val dao: TodoDao
): TodoRepository {
    override fun getAllTodos(): Flow<List<TodoItem>> {
        return dao.getAllTodos().map { list ->
            list.map { it.toDomainModel() }
        }
    }

    override suspend fun getTodoById(id: Int): TodoItem? {
        withContext(Dispatchers.IO) {
            val entity = dao.getTodoById(id) ?: return@withContext null
            return@withContext entity.toDomainModel()
        }
        return null
    }

    override suspend fun addTodo(todo: TodoItem) {
        withContext(Dispatchers.IO) {
            dao.insertTodo(todo.toEntity())
        }
    }

    override suspend fun updateTodo(todo: TodoItem) {
        withContext(Dispatchers.IO) {
            dao.updateTodo(todo.toEntity())
        }
    }

    override suspend fun deleteTodo(todo: TodoItem) {
        withContext(Dispatchers.IO) {
            dao.deleteTodo(todo.toEntity())
        }
    }
}

fun TodoEntity.toDomainModel(): TodoItem = TodoItem(
    id = this.id,
    title = this.title,
    description = this.description,
    deadline = this.deadline
)

fun TodoItem.toEntity(): TodoEntity = TodoEntity(
    id = this.id,
    title = this.title,
    description = this.description,
    deadline = this.deadline
)