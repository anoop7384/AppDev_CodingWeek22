package com.example.cwdiary.ui.fragments



import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cwdiary.R
import com.example.cwdiary.ViewModel.NotesViewModel
import com.example.cwdiary.databinding.FragmentCreateBinding
import com.example.cwdiary.model.Notes
import com.google.android.material.bottomsheet.BottomSheetDialog


import java.util.*


class CreateFragment : Fragment() {

    private lateinit var binding: FragmentCreateBinding
    val viewModel : NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCreateBinding.inflate(layoutInflater,container, false)
        setHasOptionsMenu(true)
        binding.btnSaveNotes.setOnClickListener {
            createNotes(it)
        }


        return binding.root


    }


    private fun createNotes(it: View?){
        val date = binding.editTitle.text.toString()
        val time = binding.editSubtitle.text.toString()
        val diary = binding.editNotes.text.toString()

        val d= Date()
        val realtime: CharSequence = DateFormat.format("MMMMd,yyyy",d.time)

        val data= Notes( null, title = date , subTitle = time, notes = diary, date = realtime.toString())
        viewModel.addNotes(data)

        Toast.makeText(requireContext(), "Diary Created Successfully :)", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_createFragment_to_homeFragment)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId== R.id.deleteDiary){
            val bottomSheet = BottomSheetDialog(requireContext())
            bottomSheet.setContentView(R.layout.dialog_box)
            bottomSheet.show()

            val textviewYes = bottomSheet.findViewById<TextView>(R.id.dialogYes)
            val textviewNo = bottomSheet.findViewById<TextView>(R.id.dialogNo)

            textviewYes?.setOnClickListener{
                viewModel.deleteNotes(item.itemId)

                bottomSheet.dismiss()
            }
            textviewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }



        }
        return super.onOptionsItemSelected(item)
    }

}