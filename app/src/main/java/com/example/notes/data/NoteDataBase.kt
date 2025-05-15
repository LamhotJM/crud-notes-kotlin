package com.example.notes.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Note::class], version = 1)
abstract class NoteDataBase: RoomDatabase() {
    companion object{
        const val DBNAME = "Notes"
    }
    abstract  fun getNoteDao() : NoteDao
}