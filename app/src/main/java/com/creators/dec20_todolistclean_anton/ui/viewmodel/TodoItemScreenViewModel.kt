package com.creators.dec20_todolistclean_anton.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creators.dec20_todolistclean_anton.domain.model.TodoItem
import com.creators.dec20_todolistclean_anton.domain.usecases.GetTodoUseCase
import com.creators.dec20_todolistclean_anton.domain.usecases.UpdateTodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoItemScreenViewModel @Inject constructor(
    private val getTodoUseCase: GetTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase
) : ViewModel() {
    private val _todoItem = MutableStateFlow<TodoItem?>(null)
    val todoItem = _todoItem.asStateFlow()

    fun loadTodoItem(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val todo = getTodoUseCase(id)
            _todoItem.value = todo
        }
    }

    fun updateTodo(todo: TodoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            updateTodoUseCase(todo)
        }
    }
}