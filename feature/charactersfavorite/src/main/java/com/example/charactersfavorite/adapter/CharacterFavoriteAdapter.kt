package com.example.charactersfavorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.charactersfavorite.adapter.holder.CharacterFavoriteViewHolder
import com.example.commons.ui.base.BaseListAdapter
import com.example.datasource.database.characters.favorite.CharacterFavorite

class CharactersFavoriteAdapter : BaseListAdapter<CharacterFavorite>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {

    override fun onCreateViewHolder(parent: ViewGroup, inflater: LayoutInflater, viewType: Int) =
        CharacterFavoriteViewHolder(inflater)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CharacterFavoriteViewHolder -> holder.bind(getItem(position))
        }
    }
}