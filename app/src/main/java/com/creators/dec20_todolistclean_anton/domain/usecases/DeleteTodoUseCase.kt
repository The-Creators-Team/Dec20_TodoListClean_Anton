package com.creators.dec20_todolistclean_anton.domain.usecases

import com.creators.dec20_todolistclean_anton.domain.model.TodoItem
import com.creators.dec20_todolistclean_anton.domain.repository.TodoRepository
import javax.inject.Inject

class DeleteTodoUseCase @Inject constructor(
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke(todoItem: TodoItem) {
        todoRepository.deleteTodo(todoItem)
    }
}