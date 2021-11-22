package com.dilekozgul.notesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dilekozgul.notesapp.databinding.RowLayoutBinding
import com.dilekozgul.notesapp.model.Notes

class NoteAdapter(val clickListener: NotesClickListener): ListAdapter<Notes, NoteAdapter.ViewHolder>(NoteDiffCallback) {
    companion object NoteDiffCallback: DiffUtil.ItemCallback<Notes>() {
        override fun areItemsTheSame(oldItem: Notes, newItem: Notes) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Notes, newItem: Notes) = oldItem == newItem
    }
    class ViewHolder (val binding: RowLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind (notes: Notes, clickListener: NotesClickListener) {
            binding.notes = notes
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, clickListener)
    }
}
class NotesClickListener (val clickListener: (notes: Notes) -> Unit) {
    fun onClick(notes: Notes) = clickListener(notes)
}