package com.creators.dec20_todolistclean_anton.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creators.dec20_todolistclean_anton.domain.model.TodoItem
import com.creators.dec20_todolistclean_anton.domain.usecases.AddTodoUseCase
import com.creators.dec20_todolistclean_anton.domain.usecases.DeleteTodoUseCase
import com.creators.dec20_todolistclean_anton.domain.usecases.GetAllTodosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    getAllTodosUseCase: GetAllTodosUseCase,
    private val addTodoUseCase: AddTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase
) : ViewModel() {

    val todoList = getAllTodosUseCase()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addTodo(title: String, description: String, deadLine: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            addTodoUseCase(TodoItem(title = title, description = description, deadline = deadLine))
        }
    }

    fun deleteTodo(todo: TodoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteTodoUseCase(todo)
        }
    }
}