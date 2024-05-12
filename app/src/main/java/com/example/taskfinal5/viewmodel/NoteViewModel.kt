package com.example.taskfinal5.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.taskfinal5.model.theNotes
import com.example.taskfinal5.repository.noteRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope


class NoteViewModel(app: Application , private val noteRepository: noteRepository): AndroidViewModel(app) {
    fun addNote(app_1_notes: theNotes) =
        viewModelScope.launch {
            noteRepository.insertNote(app_1_notes)
        }
    fun updateNote(app_1_notes: theNotes) =
        viewModelScope.launch {
            noteRepository.updateNote(app_1_notes)
        }
    fun deleteNote(app_1_notes: theNotes) =
        viewModelScope.launch {
            noteRepository.deleteNote(app_1_notes)
        }



    fun getAllNotes() = noteRepository.getAllNotes()

    fun searchNotes(query : String?) =
        noteRepository.searchNote(query)

}