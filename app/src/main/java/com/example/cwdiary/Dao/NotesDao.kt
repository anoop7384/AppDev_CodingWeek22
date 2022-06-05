package com.example.cwdiary.Dao


import com.example.cwdiary.model.Notes
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {

    @Query( "SELECT * FROM NOTES")
    fun getNotes(): LiveData<List<Notes>>
    @Insert(onConflict = OnConflictStrategy . REPLACE)
    fun insertNotes(notes: Notes)
    @Query( "DELETE FROM Notes WHERE id=:id")
    fun deleteNotes(id: Int)

    @Update
    fun updateNotes(notes: Notes)


}


