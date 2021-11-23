package com.dilekozgul.notesapp.view

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dilekozgul.notesapp.R
import com.dilekozgul.notesapp.databinding.FragmentUpdateBinding
import com.dilekozgul.notesapp.model.Notes
import com.dilekozgul.notesapp.viewmodel.NotesViewModel


class UpdateFragment : Fragment() {
    private val viewModel: NotesViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentUpdateBinding.inflate(inflater)

        val args = UpdateFragmentArgs.fromBundle(requireArguments())
        // Inflate the layout for this fragment
        binding.apply {
            updateEdtTask.setText(args.note.title)
            updateSpinner.setSelection(args.note.priority)

            btnUpdate.setOnClickListener {
                if (TextUtils.isEmpty(updateEdtTask.text)) {
                    Toast.makeText(requireContext(), "Title cannot be empty", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val title_str = updateEdtTask.text.toString()
                val priority = updateSpinner.selectedItemPosition

                val note = Notes(
                    args.note.id,
                    title_str,
                    priority,
                    args.note.timestamp
                )

                viewModel.update(note)

                Toast.makeText(requireContext(), "Note is updated", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_notesFragment)
            }


        }

        return binding.root
    }


}