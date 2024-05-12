package com.example.taskfinal5.fragments

import com.example.taskfinal5.R
import com.example.taskfinal5.databinding.FragmentEditNoteBinding
import com.example.taskfinal5.model.theNotes
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import androidx.core.view.MenuHost
import androidx.navigation.fragment.navArgs
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.taskfinal5.MainActivity
import com.example.taskfinal5.viewmodel.NoteViewModel
import java.time.temporal.ValueRange

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class EditNoteFragment : Fragment(R.layout.fragment_edit_note),MenuProvider {

    private var nbedit: FragmentEditNoteBinding? = null
    private val bind get() = nbedit!!

    private lateinit var nvmodel: NoteViewModel
    private lateinit var cnote : theNotes

    private val args: EditNoteFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        nbedit= FragmentEditNoteBinding.inflate(inflater,container,false)
        return bind.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mhost: MenuHost = requireActivity()
        mhost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        nvmodel = (activity as MainActivity).notes_model_view
        cnote = args.theNotes!!

        bind.titleeditnote.setText(cnote.tnote)
        bind.desedit.setText(cnote.descripofnotes)

        bind.desedit.setOnClickListener(){
            val titlen = bind.titleeditnote.text.toString().trim()
            val notedes = bind.desedit.text.toString().trim()

            if (titlen.isNotEmpty()){
                val note = theNotes(cnote.id,titlen,notedes)
                nvmodel.updateNote(note)
                view.findNavController().popBackStack(R.id.homeFragment,false)
            }else{
                Toast.makeText(context,"please enter the title",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteNote(){
        AlertDialog.Builder(activity).apply {
            setTitle("Delete Note")
            setMessage("Do you want to delete this note?")
            setPositiveButton("Delete"){_,_ ->
                nvmodel.deleteNote(cnote)
                Toast.makeText(context,"Note Deleted",Toast.LENGTH_SHORT).show()
                view?.findNavController()?.popBackStack(R.id.homeFragment,false)
            }

            setNegativeButton("Cancel",null)
        }.create().show()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_edit_note,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.deleteMenu ->{
                deleteNote()
                true
            }else->
                false
        }


        }
    override fun onDestroy() {
        super.onDestroy()
        nbedit=null
    }


}