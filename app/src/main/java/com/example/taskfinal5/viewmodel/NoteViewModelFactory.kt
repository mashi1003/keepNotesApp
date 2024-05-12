package com.example.taskfinal5.viewmodel

import android.app.Application

import com.example.taskfinal5.repository.noteRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//instantiate and return view models


class NoteViewModelFactory (val app: Application, private val noteRepository: noteRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(app, noteRepository)as T
    }
}