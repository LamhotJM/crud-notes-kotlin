package com.example.notes.screens



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.notes.data.NoteViewModel


fun checkEmptyContent(content : String) : Boolean {
    return content.trim().isNotEmpty()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewNote(navController: NavHostController,viewModel: NoteViewModel) {
    val title = remember { mutableStateOf<String>("") }
    val content = remember { mutableStateOf<String>("") }
    val savable = remember { derivedStateOf   { checkEmptyContent(content.value) } }

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    navigationIconContentColor = Color.Black,
                    titleContentColor = Color.Black,
                    actionIconContentColor = Color.Black
                ),
                title = {
                    TextField(
                        modifier = Modifier.fillMaxWidth().background(color = Color.Transparent),
                        value = title.value,
                        onValueChange = {
                            title.value = it
                        },
                        placeholder = {
                            Text("Title of the notes", style = MaterialTheme.typography.titleLarge.copy(color = Color.Gray))
                        },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = MaterialTheme.colorScheme.background,
                            focusedContainerColor = MaterialTheme.colorScheme.background,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        ),
                        textStyle = MaterialTheme.typography.titleLarge.copy(color = Color.Gray)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }

            )
        },
        floatingActionButton = {
            if (savable.value) {
                FloatingActionButton(
                    shape = CircleShape,
                    elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp),
                    onClick = {
                        viewModel.addNote(
                            title = title.value,
                            content = content.value
                        )
                        navController.popBackStack()
                    },
                    containerColor = Color.Black,
                    contentColor = Color.White
                ) {
                    Icon(
                        Icons.Default.Check, contentDescription = null
                    )
                }
            }
        },

    ){ innerPadding ->
        HorizontalDivider(modifier = Modifier.fillMaxWidth(), 1.dp, Color.Black)
        Column(
            modifier = Modifier.padding(innerPadding).padding(start = 16.dp, end = 16.dp)
        ) {
            TextField(
                value = content.value,
                onValueChange = {
                    content.value = it },
                placeholder = {
                    Text(
                        "Enter content",
                        style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray))
                        },
                modifier = Modifier.fillMaxSize(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                    focusedContainerColor = MaterialTheme.colorScheme.background,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )
        }

    }
}