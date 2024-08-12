package com.hfad.xmldisney.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.hfad.xmldisney.databinding.DisneyListItemBinding
import com.hfad.xmldisney.models.DisneyHeroList
import com.hfad.xmldisney.util.loadUrl

class DisneyViewHolder(private var binding: DisneyListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DisneyHeroList, onClick: (id: Int) -> Unit) {
        binding.run {
            characterName.text = item.name
            item.imageUrl?.let { disneyImg.loadUrl(it) }
            disneyItem.setOnClickListener {
                onClick(item.id)
            }
        }
    }
}