package com.dilekozgul.notesapp.view

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dilekozgul.notesapp.R
import com.dilekozgul.notesapp.databinding.FragmentAddBinding
import com.dilekozgul.notesapp.model.Notes
import com.dilekozgul.notesapp.viewmodel.NotesViewModel


class AddFragment : Fragment() {

    private val viewModel: NotesViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentAddBinding.inflate(inflater)
        val myAdapter = ArrayAdapter<String> (
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            resources.getStringArray(R.array.priorities)
        )
        binding.apply {
            spinner.adapter = myAdapter

            btnAdd.setOnClickListener {
                if (TextUtils.isEmpty(edtNote.text)) {
                    Toast.makeText(requireContext(), "Title cannot be empty", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val title_str = edtNote.text.toString()
                val priority = spinner.selectedItemPosition

                val notes = Notes(
                    0,
                    title_str,
                    priority,
                    System.currentTimeMillis()
                )

                viewModel.insert(notes)
                Toast.makeText(requireContext(), "Successfully added new note", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addFragment_to_notesFragment)
            }

        }

        return binding.root
    }


}