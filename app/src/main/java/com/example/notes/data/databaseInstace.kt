package com.example.notes.data

import android.app.Application
import androidx.room.Room

class NoteInstance : Application(){
    companion object{
        lateinit var noteDataBase: NoteDataBase
    }

    override fun onCreate() {
        super.onCreate()
        noteDataBase = Room.databaseBuilder(
            applicationContext,
            NoteDataBase::class.java,
            NoteDataBase.DBNAME
        ).build()
    }
}