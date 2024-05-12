package com.example.taskfinal5.fragments

import android.os.Bundle
import android.view.LayoutInflater

import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.example.taskfinal5.databinding.FragmentHomeBinding
import com.example.taskfinal5.model.theNotes
import com.example.taskfinal5.viewmodel.NoteViewModel
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import com.example.taskfinal5.MainActivity
import com.example.taskfinal5.R
import com.example.taskfinal5.adapter.NoteAdapter
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import android.view.Menu




class HomeFragment : Fragment(R.layout.fragment_home), SearchView.OnQueryTextListener,MenuProvider {

    private var homebindd : FragmentHomeBinding? = null
    private val b_bind get() = homebindd!!

    private lateinit var noteview_model: NoteViewModel
    private lateinit var notead: NoteAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        // Inflate the layout for this fragment
        homebindd=FragmentHomeBinding.inflate(inflater,container,false)


        val menuHost : MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner,Lifecycle.State.RESUMED)

        noteview_model = (activity as MainActivity).notes_model_view
        setupHomeRecyclerView()

        b_bind.buttonadd.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)
        }
        return b_bind.root


    }
    private fun updateUI(note : List<theNotes>?){
        if (note != null){
            if(note.isNotEmpty()){
                b_bind.imgn.visibility = View.GONE
                b_bind.cyclerview.visibility = View.VISIBLE

            }else{
                b_bind.imgn.visibility = View.VISIBLE
                b_bind.cyclerview.visibility = View.GONE
            }
        }

    }

    private fun setupHomeRecyclerView(){
        notead = NoteAdapter()
        b_bind.cyclerview.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = notead
        }

        activity?.let{
            noteview_model.getAllNotes().observe(viewLifecycleOwner){
                note ->
                notead.dif.submitList(note)
                updateUI(note)
            }
        }
    }
    private fun searchNote(query: String?){
        val searchQuery = "%$query"

        noteview_model.searchNotes(searchQuery).observe(this){ list->
            notead.dif.submitList(list)

        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText!=null){
            searchNote(newText)
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        homebindd = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.home_menu, menu)

        val menuSearch = menu.findItem(R.id.searchMenu).actionView as SearchView
        menuSearch.isSubmitButtonEnabled = false
        menuSearch.setOnQueryTextListener(this)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return false
    }


}