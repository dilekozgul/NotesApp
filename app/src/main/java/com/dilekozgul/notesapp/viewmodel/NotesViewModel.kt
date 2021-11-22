package com.dilekozgul.notesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.dilekozgul.notesapp.model.Notes
import com.dilekozgul.notesapp.repository.NoteRepository
import com.dilekozgul.notesapp.room.NoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel (application: Application) : AndroidViewModel(application) {

    private val noteDao = NoteDatabase.getDatabase(application).noteDao()
    private val repository: NoteRepository
    val getAllNotes: LiveData<List<Notes>>
    val getAllNotesByPriority: LiveData<List<Notes>>

    init {
        repository = NoteRepository(noteDao)
        getAllNotes = repository.getAllNotes()
        getAllNotesByPriority = repository.getAllNotesByPriority()
    }

    fun insert (notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNote(notes)
        }
    }

    fun delete (notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(notes)
        }
    }

    fun update (notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(notes)
        }
    }

    fun deleteAll () {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllNotes()
        }
    }
}