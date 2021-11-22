package com.dilekozgul.notesapp.repository

import androidx.lifecycle.LiveData
import com.dilekozgul.notesapp.model.Notes
import com.dilekozgul.notesapp.room.NoteDao

class NoteRepository (val noteDao: NoteDao){

    suspend fun insertNote (notes: Notes) = noteDao.insert(notes)

    suspend fun updateNote(notes: Notes) = noteDao.update(notes)

    suspend fun deleteNote(notes: Notes) = noteDao.delete(notes)

    suspend fun deleteAllNotes () {
        //
        noteDao.deleteAll()
        //
    }
    fun getAllNotes (): LiveData<List<Notes>> = noteDao.getAllNotes()

    fun getAllNotesByPriority () : LiveData<List<Notes>> = noteDao.getAllNotesByPriority()

}