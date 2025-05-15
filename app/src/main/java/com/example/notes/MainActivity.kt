package com.example.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import com.example.notes.data.NoteViewModel
import com.example.notes.ui.theme.NotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Set the status bar icons to black
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
        val notesViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        setContent {
            NotesTheme {
                Surface {
                    AppNavigation(notesViewModel)
                }
            }
        }
    }
}