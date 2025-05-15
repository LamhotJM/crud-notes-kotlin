package com.example.notes.data


import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch



class NoteViewModel : ViewModel() {
    val noteDao = NoteInstance.noteDataBase.getNoteDao()
    private val _allNotes: LiveData<List<Note>> = noteDao.getNotes()

    private val _searchQuery = mutableStateOf("")

    private val _searchedNotes = MutableLiveData<List<Note>>()
    val noteList: LiveData<List<Note>> = _searchedNotes

    init {
        viewModelScope.launch {
            _allNotes.asFlow().collectLatest { notes ->
                filterNotes(notes, _searchQuery.value)
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        filterNotes(_allNotes.value.orEmpty(), query)
    }

    private fun filterNotes(allNotes: List<Note>, query: String) {
        val lowerCaseQuery = query.lowercase()
        val filteredList = allNotes.filter {
            it.title.lowercase().contains(lowerCaseQuery) || it.content.lowercase().contains(lowerCaseQuery)
        }
        _searchedNotes.value = filteredList
    }

    fun addNote (title: String, content: String){
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.addNote(
                Note(
                    title = title,
                    content = content,
                    createdAt = DateManager.formatNoteDate(DateManager.now()),
                    color = Note.fromColor()
                )
            )
        }
    }

    fun editNote(id: Int,title: String,content: String){
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.editNote(
                id = id,
                title = title,
                content = content
            )
        }
    }
    fun deleteNote (id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.deleteNote(id = id)
        }
    }

    fun getNoteColor(noteId: Int) : Int  {
        var color = 0
        _allNotes.value?.map { note ->
            if (note.id == noteId){
                color = note.color
            }
        }
        return color
    }
}