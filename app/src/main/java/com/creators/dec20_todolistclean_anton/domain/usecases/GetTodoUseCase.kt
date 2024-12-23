package com.creators.dec20_todolistclean_anton.domain.usecases

import com.creators.dec20_todolistclean_anton.domain.model.TodoItem
import com.creators.dec20_todolistclean_anton.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTodoUseCase @Inject constructor(
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke(id: Int): TodoItem? {
        return todoRepository.getTodoById(id)
    }
}