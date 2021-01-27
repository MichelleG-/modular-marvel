package com.example.characterslist.detail.di

import androidx.annotation.VisibleForTesting
import com.example.characterslist.detail.model.CharacterDetailMapper
import com.example.characterslist.detail.ui.CharacterDetailFragment
import com.example.characterslist.detail.ui.CharacterDetailViewModel
import com.example.characterslist.list.pagging.CharactersPageDataSourceFactory
import com.example.characterslist.list.ui.CharactersListViewModel
import com.example.datasource.database.characters.favorite.CharacterFavoriteRepository
import com.example.datasource.di.network.repositorys.CharactersRepository
import com.example.datasource.di.scope.FeatureScope
import com.vmadalin.commons.views.ProgressBarDialog
import dagger.Module
import dagger.Provides

@Module
class CharacterDetailModule(
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val fragment: CharacterDetailFragment
) {

    @FeatureScope
    @Provides
    fun providesCharacterDetailViewModel(
        marvelRepository: CharactersRepository,
        characterFavoriteRepository: CharacterFavoriteRepository,
        characterDetailMapper: CharacterDetailMapper
    ) : CharacterDetailViewModel {
        fragment.viewModel = CharacterDetailViewModel(  marvelRepository = marvelRepository,
            characterFavoriteRepository = characterFavoriteRepository,
            characterDetailMapper = characterDetailMapper)
        return fragment.viewModel
    }

    @FeatureScope
    @Provides
    fun providesCharacterDetailMapper() = CharacterDetailMapper()

    @FeatureScope
    @Provides
    fun providesProgressBarDialog() = ProgressBarDialog(fragment.requireContext())
}
