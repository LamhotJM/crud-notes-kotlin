package com.example.notes.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
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
import com.example.notes.data.Note
import com.example.notes.data.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Edit(
    navController: NavHostController,
    note: Note?,
    viewModel: NoteViewModel,
    noteId: Int
) {
    val title = remember { mutableStateOf<String>(note?.title ?: "") }
    val content = remember { mutableStateOf<String>(note?.content ?: "") }
    val savable = remember { derivedStateOf   { checkEmptyContent(content.value) } }
    val colorInt = viewModel.getNoteColor(noteId)

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background( color = Color(colorInt)
            ),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(colorInt),
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
                            Text("Title", style = MaterialTheme.typography.titleLarge.copy(color = Color.Gray))
                        },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color(colorInt),
                            focusedContainerColor = Color(colorInt),
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
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                },

            )
            Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.Black))
        },
        floatingActionButton = {
            if (savable.value) {
                FloatingActionButton(
                    shape = CircleShape,
                    elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp),
                    onClick = {
                        viewModel.editNote(
                            id = noteId,
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
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            TextField(
                value = content.value,
                onValueChange = {
                    content.value = it },
                placeholder = {
                    Text(
                        colorInt.toString(),
                        style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray))
                },
                modifier = Modifier.fillMaxSize(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(colorInt),
                    focusedContainerColor = Color(colorInt),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )
        }

    }
}