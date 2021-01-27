package com.example.charactersfavorite

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.charactersfavorite.adapter.CharactersFavoriteAdapter
import com.example.charactersfavorite.adapter.CharactersFavoriteTouchHelper
import com.example.charactersfavorite.databinding.FragmentCharactersFavoriteBinding
import com.example.charactersfavorite.di.CharacterFavoriteModule
import com.example.charactersfavorite.di.DaggerCharacterFavoriteComponent
import com.example.commons.ui.base.BaseFragment
import com.example.commons.ui.extensions.observe
import com.example.datasource.database.characters.favorite.CharacterFavorite
import com.example.sample.SampleApp
import javax.inject.Inject

class CharacterFavoriteFragment :
    BaseFragment<FragmentCharactersFavoriteBinding, CharactersFavoriteViewModel>(
        layoutId = R.layout.fragment_characters_favorite
    ) {

    @Inject
    lateinit var viewAdapter: CharactersFavoriteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.data, ::onViewDataChange)
    }

    override fun onInitDependencyInjection() {
        DaggerCharacterFavoriteComponent
            .builder()
            .dataSourceComponent(SampleApp.dataSourceComponent(requireContext()))
            .characterFavoriteModule(CharacterFavoriteModule(this))
            .build()
            .inject(this)
    }

    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
        viewBinding.includeList.charactersFavoriteList.apply {
            adapter = viewAdapter

            ItemTouchHelper(
                CharactersFavoriteTouchHelper {
                    viewModel.removeFavoriteCharacter(viewAdapter.currentList[it])
                }
            ).attachToRecyclerView(this)
        }
    }

    private fun onViewDataChange(viewData: List<CharacterFavorite>) {
        viewAdapter.submitList(viewData)
    }
}