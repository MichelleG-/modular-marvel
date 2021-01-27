package com.example.charactersfavorite.adapter.holder

import android.view.LayoutInflater
import com.example.charactersfavorite.databinding.ListItemCharactersFavoriteBinding
import com.example.commons.ui.base.BaseViewHolder
import com.example.datasource.database.characters.favorite.CharacterFavorite

class CharacterFavoriteViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemCharactersFavoriteBinding>(
    binding = ListItemCharactersFavoriteBinding.inflate(inflater)
) {

    fun bind(characterFavorite: CharacterFavorite) {
        binding.character = characterFavorite
        binding.executePendingBindings()
    }
}
