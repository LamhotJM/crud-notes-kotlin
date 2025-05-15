package com.example.notes.data


import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notes.ui.theme.LightAqua
import com.example.notes.ui.theme.LightBrown
import com.example.notes.ui.theme.LightGreen
import com.example.notes.ui.theme.LightPink
import kotlinx.datetime.LocalDateTime


@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String,
    var content: String,
    var createdAt: String,
    var color: Int
) {
    companion object {
        fun fromColor(): Int  {
            val colors = listOf<Color>(LightPink, LightAqua, LightBrown, LightGreen)
            return colors.random().toArgb()
        } // Convert Color to Int
    }

}