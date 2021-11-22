package com.dilekozgul.notesapp.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.dilekozgul.notesapp.R
import com.dilekozgul.notesapp.adapter.NoteAdapter
import com.dilekozgul.notesapp.adapter.NotesClickListener
import com.dilekozgul.notesapp.databinding.FragmentNotesBinding
import com.dilekozgul.notesapp.viewmodel.NotesViewModel
import com.google.android.material.snackbar.Snackbar


class NotesFragment : Fragment() {

    private val viewModel: NotesViewModel by viewModels()
    private lateinit var adapter: NoteAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentNotesBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        // Inflate the layout for this fragment


        adapter = NoteAdapter(NotesClickListener { notes ->
            findNavController().navigate(NotesFragmentDirections.actionNotesFragmentToUpdateFragment(notes))

        })

        viewModel.getAllNotes.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        binding.apply {

            binding.recyclerView.adapter = adapter

            floatingActionButton.setOnClickListener {
                findNavController().navigate(R.id.action_notesFragment_to_addFragment)
            }
        }
        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val note = adapter.currentList[position]
                viewModel.delete(note)

                Snackbar.make(binding.root, "Note is deleted", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewModel.insert(note)
                    }

                    show()
                }
            }
        })
            .attachToRecyclerView(binding.recyclerView)
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.notes_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_delete_all -> deleteAllItems()

            R.id.action_priority -> viewModel.getAllNotesByPriority.observe(viewLifecycleOwner, { tasks ->
                adapter.submitList(tasks)
            })
        }


        return super.onOptionsItemSelected(item)
    }
    private fun deleteAllItems () {

        AlertDialog.Builder(requireContext())
            .setTitle("Deleting All")
            .setMessage("Are you sure?")
            .setPositiveButton("Yes") { dialog, _ ->
                viewModel.deleteAll()
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()

    }


}