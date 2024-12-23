package com.creators.dec20_todolistclean_anton.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.creators.dec20_todolistclean_anton.ui.viewmodel.TodoItemScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTodoScreen(
    navController: NavController,
    todoId: Int,
    viewModel: TodoItemScreenViewModel = hiltViewModel()
) {
    val todoItem by viewModel.todoItem.collectAsState()

    LaunchedEffect(todoId) {
        viewModel.loadTodoItem(todoId)
    }

    var title by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue(""))}

    LaunchedEffect(todoItem) {
        todoItem?.let {
            title = TextFieldValue(it.title)
            description = TextFieldValue(it.description)
        }
    }

    Scaffold(
        topBar = {TopAppBar(title = { Text("Edit Todo") }) },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(it),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    todoItem?.let {
                        viewModel.updateTodo(
                            it.copy(title = title.text, description = description.text)
                        )
                        navController.popBackStack()
                    }
                }
            ) {
                Text("Save")
            }
        }
    }
}