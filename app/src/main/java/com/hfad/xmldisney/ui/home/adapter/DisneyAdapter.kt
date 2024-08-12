package com.hfad.xmldisney.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hfad.xmldisney.databinding.DisneyListItemBinding
import com.hfad.xmldisney.models.DisneyHeroList

class DisneyAdapter(private val onClick: (id: Int) -> Unit) :
    ListAdapter<DisneyHeroList, DisneyViewHolder>(object :
        DiffUtil.ItemCallback<DisneyHeroList>() {
        override fun areItemsTheSame(
            oldItem: DisneyHeroList,
            newItem: DisneyHeroList
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: DisneyHeroList,
            newItem: DisneyHeroList
        ): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisneyViewHolder {
        return DisneyViewHolder(
            DisneyListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DisneyViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }
}