package com.example.notes.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.notes.Screen
import com.example.notes.data.Note
import com.example.notes.data.NoteViewModel



@Composable
fun Home(
    notes: List<Note>,
    navController: NavHostController,
    viewModel: NoteViewModel
) {
    val search = remember { mutableStateOf(false) }
    val  searchVal = remember { mutableStateOf("") }
    Scaffold (
        topBar = {
            AppBar(search = search, searchText = searchVal, viewModel = viewModel )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                navController.navigate(Screen.New.route) },
                containerColor = Color.Black
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = ""
                )
            }
        }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(start = 16.dp, end = 16.dp)
        ) {
            if (notes.isNotEmpty() ){
                LazyColumn {
                    items  (notes){ note ->
                        Box(
                            modifier = Modifier.padding(top = 16.dp )
                        ){
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .fillMaxWidth()
                                    .height(120.dp)
                                    .background(Color(note.color))
                                    .clickable(
                                        onClick = {
                                            navController.navigate(
                                                Screen.Edit.route.replace(
                                                    "{noteId}",
                                                    note.id.toString()
                                                )
                                            )
                                            viewModel.getNoteColor(note.id)
                                        }
                                    )

                            ){
                                if (note.title.isNotEmpty()) {

                                    Text(
                                        note.title.uppercase(), style = MaterialTheme.typography.titleMedium.copy(color = Color.Black),
                                        overflow = TextOverflow.Clip,
                                        maxLines = 1,
                                        modifier = Modifier
                                            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                                            .align(Alignment.TopStart)
                                    )

                                }
                                IconButton(
                                    onClick = {
                                        viewModel.deleteNote(note.id)
                                    },
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                ) {
                                    Icon(Icons.Default.Delete, contentDescription = null, tint = Color.Black, modifier = Modifier.width(16.dp).height(16.dp))
                                }
                                Text(
                                    note.content, style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 3,
                                    modifier = Modifier
                                        .align(alignment = Alignment.CenterStart)
                                        .padding(
                                            start = 16.dp,
                                            end = 16.dp,
                                            top = if (note.title.isNotEmpty()) 0.dp else 16.dp
                                        )
                                )

                                Text(
                                    note.createdAt,
                                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Black),
                                    modifier = Modifier
                                        .padding(bottom = 10.dp, end = 16.dp)
                                        .align(
                                            alignment = Alignment.BottomEnd
                                        )
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}