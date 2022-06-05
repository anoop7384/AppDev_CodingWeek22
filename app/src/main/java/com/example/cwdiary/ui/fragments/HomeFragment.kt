package com.example.cwdiary.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.WithHint
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.cwdiary.R
import com.example.cwdiary.ViewModel.NotesViewModel
import com.example.cwdiary.databinding.FragmentHomeBinding
import com.example.cwdiary.model.Notes
import com.example.cwdiary.ui.Adapter.NotesAdapter


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: NotesViewModel by viewModels()
//    var oldNotes= arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = com.example.cwdiary.databinding.FragmentHomeBinding.inflate(
            layoutInflater,
            container,
            false
        )
        setHasOptionsMenu(true)

//        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2 ,LinearLayoutManager.VERTICAL)

        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
//            oldNotes = notesList as ArrayList<Notes> /* = java.util.ArrayList<com.example.cwdiary.model.Notes> */

            binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rcvAllNotes.adapter = NotesAdapter(requireContext(),notesList)
        }


        binding.btnAddNotes.setOnClickListener {

            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createFragment)
        }


        // Inflate the layout for this fragment
        return binding.root
    }

// Tried making search bar but app is crashing : (

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
//
//        val item=menu.findItem(R.id.app_bar_search)
//        val searchView= item.actionView as SearchView
//////
//        searchView.queryHint ="Enter The Date ..."

//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(po: String?): Boolean {
//
//
//
//                return false
//
//            }
//
//            override fun onQueryTextChange(p0: String?): Boolean {
////                if (p0 != null) {
////                    NotesFiltering(p0)
////                }
//
//                return true
//            }
////
//        })
//
//        super.onCreateOptionsMenu(menu, inflater)
//    }
//
//    private fun NotesFiltering(p0: String?){
//        val newFilteredList = arrayListOf<Notes>()
//        for (i in oldNotes){
//            if(i.title.contains(p0!!)){
//                newFilteredList.add(i)
//            }
//        }
//        adapter.filtering(newFilteredList)
//
    }
}