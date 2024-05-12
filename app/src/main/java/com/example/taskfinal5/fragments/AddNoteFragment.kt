package com.example.taskfinal5.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import com.example.taskfinal5.databinding.FragmentAddNoteBinding
import com.example.taskfinal5.model.theNotes
import com.example.taskfinal5.viewmodel.NoteViewModel
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import android.view.View
import com.example.taskfinal5.MainActivity
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.taskfinal5.R


class AddNoteFragment : Fragment(R.layout.fragment_add_note),MenuProvider {

    private var baddnotes: FragmentAddNoteBinding? = null
    private val b get() =baddnotes!!


    private lateinit var noteview: NoteViewModel
    private lateinit var addNoteView: View



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        baddnotes = FragmentAddNoteBinding.inflate(inflater,container,false)
        return b.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost : MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        noteview = (activity as MainActivity).notes_model_view
        addNoteView = view

    }

    private fun saveNote(view: View){
        val titleofnote = b.addnoteTitle.text.toString().trim()
        val noteofdesc = b.andes.text.toString().trim()

        if (titleofnote.isNotEmpty()){
            val note = theNotes(0,titleofnote,noteofdesc)
            noteview.addNote(note)

            Toast.makeText(addNoteView.context,"note is saved",Toast.LENGTH_SHORT).show()
            view.findNavController().popBackStack(R.id.homeFragment,false)

        }
        else{
            Toast.makeText(addNoteView.context,"enter the title of the noter",Toast.LENGTH_SHORT).show()

        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_add_note,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.saveMenu -> {
                saveNote(addNoteView)
                true
            }

            else ->
                false

        }

    }
        override fun onDestroy() {
            super.onDestroy()
            baddnotes = null
        }
    }


