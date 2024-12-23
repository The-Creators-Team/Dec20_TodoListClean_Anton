package com.creators.dec20_todolistclean_anton.domain.usecases

import com.creators.dec20_todolistclean_anton.domain.model.TodoItem
import com.creators.dec20_todolistclean_anton.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTodosUseCase @Inject constructor(
    private val todoRepository: TodoRepository
) {
    operator fun invoke(): Flow<List<TodoItem>> {
        return todoRepository.getAllTodos()
    }
}