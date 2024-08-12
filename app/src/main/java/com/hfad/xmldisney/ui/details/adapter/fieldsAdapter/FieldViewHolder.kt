package com.hfad.xmldisney.ui.details.adapter.fieldsAdapter

import androidx.recyclerview.widget.RecyclerView
import com.hfad.xmldisney.databinding.MovieItemBinding

class FieldViewHolder(private val binding: MovieItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String) {
        binding.run {
            movieName.text = item
        }
    }
}