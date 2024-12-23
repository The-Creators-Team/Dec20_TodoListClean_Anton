package com.creators.dec20_todolistclean_anton.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.creators.dec20_todolistclean_anton.ui.screens.EditTodoScreen
import com.creators.dec20_todolistclean_anton.ui.screens.LoginScreen
import com.creators.dec20_todolistclean_anton.ui.screens.SignUpScreen
import com.creators.dec20_todolistclean_anton.ui.screens.TodoListScreen

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Signup : Screen("signup")
    object ResetPassword : Screen("reset_password")
    object TodoList : Screen("todo_list")
    object EditTodo : Screen("edit_todo/{todoId}") {
        fun createRoute(todoId: Int) = "edit_todo/$todoId"
    }
}

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.Signup.route) {
            SignUpScreen(navController = navController)
        }
        composable(Screen.TodoList.route) {
            TodoListScreen(navController = navController)
        }
        composable(Screen.EditTodo.route) { backStackEntry ->
            val todoId =
                backStackEntry.arguments?.getString("todoId")?.toIntOrNull() ?: 0
            EditTodoScreen(navController = navController, todoId = todoId)
        }
    }
}