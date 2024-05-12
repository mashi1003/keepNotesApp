package com.example.taskfinal5.adapter

import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.taskfinal5.fragments.HomeFragmentDirections
import com.example.taskfinal5.model.theNotes
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer

import androidx.recyclerview.widget.RecyclerView
import com.example.taskfinal5.databinding.NoteLayoutBinding
import androidx.recyclerview.widget.DiffUtil
import com.example.taskfinal5.fragments.HomeFragment



class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){

    class NoteViewHolder(val itemBinding: NoteLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root)

    private val diffcb = object : DiffUtil.ItemCallback<theNotes>(){        //ditermine the difference between the two list
        override fun areItemsTheSame(oldItem: theNotes, newItem: theNotes): Boolean {       //is there same item
            return oldItem.id == newItem.id &&
                    oldItem.descripofnotes ==newItem.descripofnotes &&
                    oldItem.tnote == newItem.tnote
        }

        override fun areContentsTheSame(oItem: theNotes, nItem: theNotes): Boolean {
            return oItem == nItem
        }

    }
    val dif = AsyncListDiffer(this,diffcb)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            NoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dif.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = dif.currentList[position]

        holder.itemBinding.ntitle.text = currentNote.tnote
        holder.itemBinding.noteDesc.text = currentNote.descripofnotes

        holder.itemView.setOnClickListener{
            val dir1 = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(currentNote)
            it.findNavController().navigate(dir1)
        }
    }
}