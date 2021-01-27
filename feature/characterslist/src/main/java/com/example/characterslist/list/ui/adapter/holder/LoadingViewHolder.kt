package com.example.characterslist.list.ui.adapter.holder

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.characterslist.databinding.ListItemLoadingBinding
import com.example.commons.ui.base.BaseViewHolder

/**
 * Class describes characters loading view and metadata about its place within the [RecyclerView].
 *
 * @see BaseViewHolder
 */
class LoadingViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemLoadingBinding>(
    binding = ListItemLoadingBinding.inflate(inflater)
)
