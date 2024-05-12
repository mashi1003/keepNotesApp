package com.example.taskfinal5.repository

import androidx.room.Query
import com.example.taskfinal5.database.NoteDatabase


import com.example.taskfinal5.model.theNotes

class noteRepository(private val db:NoteDatabase) {

    suspend fun insertNote(app_notes: theNotes) = db.getNoteDoa().InsertNote(app_notes)//called he insert methos in notedoa in notedatabse
    suspend fun updateNote(app_notes: theNotes) = db.getNoteDoa().updateNote(app_notes)//called he update methos in notedoa in notedatabse
    suspend fun deleteNote(app_notes: theNotes) = db.getNoteDoa().deleteNote(app_notes)//called he delete methos in notedoa in notedatabse

    fun getAllNotes() = db.getNoteDoa().getAllNotes()
    fun searchNote(query: String?) = db.getNoteDoa().searchNote(query)





}