package com.example.cwdiary.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cwdiary.R
import com.example.cwdiary.databinding.ItemNotesBinding
import com.example.cwdiary.model.Notes


class NotesAdapter(val requireContext: Context, private var notesList:List<Notes>):
    RecyclerView.Adapter<NotesAdapter.notesViewHolder>(){
//     fun filtering(newFilteredList: ArrayList<Notes>) {
//         notesList= newFilteredList
//         notifyDataSetChanged()
//     }

    class notesViewHolder(val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): notesViewHolder {
        return notesViewHolder(
            ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder:notesViewHolder,position:Int){
        val data= notesList[position]
            holder.binding.notesTitle.text = data.title
            holder.binding.notesSubtitle.text = data.subTitle
            holder.binding.notesDiary.text = data.notes

        holder.binding.root.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_editFragment)
        }
    }
    override fun getItemCount()= notesList.size

}