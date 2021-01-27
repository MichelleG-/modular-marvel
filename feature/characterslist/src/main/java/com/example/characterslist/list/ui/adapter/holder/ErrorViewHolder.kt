package com.example.characterslist.list.ui.adapter.holder

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.characterslist.databinding.ListItemErrorBinding
import com.example.characterslist.list.ui.CharactersListViewModel
import com.example.commons.ui.base.BaseViewHolder

/**
 * Class describes characters error view and metadata about its place within the [RecyclerView].
 *
 * @see BaseViewHolder
 */
class ErrorViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemErrorBinding>(
    binding = ListItemErrorBinding.inflate(inflater)
) {

    /**
     * Bind view data binding variables.
     *
     * @param viewModel character list view model.
     */
    fun bind(viewModel: CharactersListViewModel) {
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }
}
