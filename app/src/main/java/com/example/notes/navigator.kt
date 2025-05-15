package com.example.notes

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notes.data.NoteViewModel
import com.example.notes.screens.Edit
import com.example.notes.screens.Home
import com.example.notes.screens.NewNote

sealed class Screen(val route : String) {
    object Home : Screen("home")
    object New : Screen("new")
    object Edit : Screen("edit/{noteId}")
}

@Composable
fun AppNavigation(viewModel: NoteViewModel) {
    val navController = rememberNavController()
    val notes = remember { viewModel.noteList }.observeAsState(emptyList())

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(Screen.Home.route){
            Home(
                notes = notes.value,
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(
            Screen.Edit.route,
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId")
            val note = notes.value.find { it.id == noteId }
            if (noteId != null) {
                Edit(
                    noteId = noteId,
                    navController = navController,
                    viewModel = viewModel,
                    note = note
                )
            } else {
                // Handle the case where noteId is null (shouldn't happen if navigation is correct)
                Text("Error: Note ID not found")
            }
        }
        composable(Screen.New.route) {
            NewNote(navController = navController, viewModel = viewModel)
        }
    }
}
