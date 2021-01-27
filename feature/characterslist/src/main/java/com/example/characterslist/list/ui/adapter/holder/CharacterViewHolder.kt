package com.example.characterslist.list.ui.adapter.holder

import android.view.LayoutInflater
import com.example.characterslist.databinding.ListItemCharacterBinding
import com.example.characterslist.list.model.CharacterItem
import com.example.characterslist.list.ui.CharactersListViewModel
import com.example.commons.ui.base.BaseViewHolder

/**
 * Class describes character view and metadata about its place within the [RecyclerView].
 *
 * @see BaseViewHolder
 */
class CharacterViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemCharacterBinding>(
    binding = ListItemCharacterBinding.inflate(inflater)
) {

    /**
     * Bind view data binding variables.
     *
     * @param viewModel Character list view model.
     * @param item Character list item.
     */
    fun bind(viewModel: CharactersListViewModel, item: CharacterItem) {
        binding.viewModel = viewModel
        binding.character = item
        binding.executePendingBindings()
    }
}
