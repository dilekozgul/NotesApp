package com.dilekozgul.notesapp.room


import androidx.lifecycle.LiveData
import androidx.room.*
import com.dilekozgul.notesapp.model.Notes

@Dao
interface NoteDao {
    @Insert
    suspend fun insert (notes: Notes)

    @Delete
    suspend fun delete (notes: Notes)

    @Update
    suspend fun update (notes: Notes)

    @Query("DELETE FROM notes_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM notes_table ORDER BY timestamp DESC")
    fun getAllNotes (): LiveData<List<Notes>>

    @Query("SELECT * FROM notes_table ORDER BY priority ASC")
    fun getAllNotesByPriority (): LiveData<List<Notes>>
}