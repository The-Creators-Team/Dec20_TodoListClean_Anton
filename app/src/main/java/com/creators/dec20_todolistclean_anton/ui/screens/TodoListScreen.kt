package com.creators.dec20_todolistclean_anton.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.creators.dec20_todolistclean_anton.domain.model.TodoItem
import com.creators.dec20_todolistclean_anton.ui.navigation.Screen
import com.creators.dec20_todolistclean_anton.ui.viewmodel.TodoListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    navController: NavHostController,
    viewModel: TodoListViewModel = hiltViewModel<TodoListViewModel>()
) {
    val todos by viewModel.todoList.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Todo List") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // For simplicity, let's create a quick new item
                // or navigate to a new screen where user inputs data
                viewModel.addTodo("New Todo", "Description", System.currentTimeMillis())
            }) {
                Text("+")
            }
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize().padding(it),
        ) {
            items(todos) { todo ->
                TodoRow(
                    todo = todo,
                    onEdit = {
                        navController.navigate(Screen.EditTodo.createRoute(todo.id))
                    },
                    onDelete = {
                        viewModel.deleteTodo(todo)
                    }
                )
                Divider()
            }
        }
    }
}

@Composable
fun TodoRow(
    todo: TodoItem,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = todo.title, style = MaterialTheme.typography.titleMedium)
        Text(text = "Description: ${todo.description}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Deadline: ${todo.deadline}", style = MaterialTheme.typography.bodySmall)
        Row {
            TextButton(onClick = onEdit) {
                Text("Edit")
            }
            TextButton(onClick = onDelete) {
                Text("Delete")
            }
        }
    }
}