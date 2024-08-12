package com.hfad.xmldisney.ui.details.adapter.heroAdapter

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hfad.xmldisney.ui.details.adapter.fieldsAdapter.FieldAdapter
import com.hfad.xmldisney.databinding.CharacterFieldBinding
import com.hfad.xmldisney.models.Fields

class HeroViewHolder(private val binding: CharacterFieldBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Fields) {
        binding.run {
                fieldName.text = binding.root.context.getString(item.nameRes)
                rwFieldsItems.layoutManager = GridLayoutManager(binding.root.context, 3)
                val adapter = FieldAdapter()
                rwFieldsItems.adapter = adapter
                adapter.submitList(item.list)
        }
    }
}