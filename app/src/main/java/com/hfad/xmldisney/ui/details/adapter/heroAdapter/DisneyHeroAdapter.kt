package com.hfad.xmldisney.ui.details.adapter.heroAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hfad.xmldisney.databinding.CharacterFieldBinding
import com.hfad.xmldisney.models.Fields

class DisneyHeroAdapter : ListAdapter<Fields, HeroViewHolder>(object :
    DiffUtil.ItemCallback<Fields>() {

    override fun areItemsTheSame(oldItem: Fields, newItem: Fields): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: Fields, newItem: Fields): Boolean {
        return false
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        return HeroViewHolder(
            CharacterFieldBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}