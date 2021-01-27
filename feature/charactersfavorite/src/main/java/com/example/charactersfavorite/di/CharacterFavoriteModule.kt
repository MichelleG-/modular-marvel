package com.example.charactersfavorite.di

import androidx.annotation.VisibleForTesting
import com.example.charactersfavorite.CharacterFavoriteFragment
import com.example.charactersfavorite.CharactersFavoriteViewModel
import com.example.charactersfavorite.adapter.CharactersFavoriteAdapter
import com.example.datasource.database.characters.favorite.CharacterFavoriteRepository
import com.example.datasource.di.scope.FeatureScope
import dagger.Module
import dagger.Provides

@Module
class CharacterFavoriteModule(
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val fragment: CharacterFavoriteFragment
) {

    @FeatureScope
    @Provides
    fun providesCharactersFavoriteViewModel(
        characterFavoriteRepository: CharacterFavoriteRepository) : CharactersFavoriteViewModel{
        fragment.viewModel = CharactersFavoriteViewModel(characterFavoriteRepository = characterFavoriteRepository)
        return fragment.viewModel
    }

    @Provides
    @FeatureScope
    fun providesCharactersFavoriteAdapter() = CharactersFavoriteAdapter()
}
s