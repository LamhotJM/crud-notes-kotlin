package com.example.notes.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {
    @Query("SELECT * FROM Note")
    fun getNotes () : LiveData<List<Note>>

    @Insert
    fun addNote (note : Note)

    @Query("UPDATE Note SET title = :title, content = :content WHERE id = :id")
    fun editNote (id : Int, title : String, content : String)

    @Query("DELETE FROM Note WHERE id = :id")
    fun deleteNote(id : Int)


    @Query("SELECT * FROM note WHERE title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%'")
    fun searchNotes(query: String): LiveData<List<Note>>
}