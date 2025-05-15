package com.example.notes.screens


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.notes.data.NoteViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    search: MutableState<Boolean>,
    searchText: MutableState<String>,
    viewModel: NoteViewModel
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            navigationIconContentColor = Color.Black,
            titleContentColor = Color.Black,
            actionIconContentColor = Color.Black
        ),
        title = {
            if (!search.value) {
                Text("List Notes by Lamhot Siagian")
            } else {
                TextField(
                    value = searchText.value,
                    onValueChange = { newValue ->
                        searchText.value = newValue
                        viewModel.updateSearchQuery(newValue)
                    },
                    placeholder = { Text("Search...") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.background,
                        unfocusedContainerColor = MaterialTheme.colorScheme.background,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedTextColor = Color.Black
                    ),
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    search.value = !search.value
                    if (!search.value) {
                        searchText.value = ""
                    }
                }
            ) {
                if (!search.value) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = null
                    )
                }

            }
        }
    )
}