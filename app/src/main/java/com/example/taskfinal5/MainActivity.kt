package com.example.taskfinal5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.taskfinal5.database.NoteDatabase
import com.example.taskfinal5.repository.noteRepository
import com.example.taskfinal5.viewmodel.NoteViewModel
import com.example.taskfinal5.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var notes_model_view: NoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViewModel()
    }

    private fun setUpViewModel(){
        val reponote = noteRepository(NoteDatabase(this))
        val viewModelProviderFactory = NoteViewModelFactory(application,reponote)
        notes_model_view = ViewModelProvider(this, viewModelProviderFactory)[NoteViewModel::class.java]

    }

}