package com.example.taskfinal5.database

import androidx.lifecycle.LiveData
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert

import com.example.taskfinal5.model.theNotes

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)        //if there is a conflict old data will replace the new data
    suspend fun InsertNote(n: theNotes)

    @Update
    suspend fun updateNote(n: theNotes)

    @Delete
    suspend fun deleteNote(n: theNotes)

    @Query("SELECT * FROM theNotes ORDER BY id DESC")      //first created at the botton and last created at the top
    fun getAllNotes():LiveData<List<theNotes>>

    @Query("SELECT * FROM theNotes WHERE tnote LIKE :query OR descripofnotes LIKE descripofnotes LIKE :query")
    fun searchNote(query: String?): LiveData<List<theNotes>>
}