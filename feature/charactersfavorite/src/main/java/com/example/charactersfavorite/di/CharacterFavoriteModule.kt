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

    @Provides
    @FeatureScope
    fun providesCharactersFavoriteViewModel(
        characterFavoriteRepository: CharacterFavoriteRepository
    ) = fragment.viewModel.apply {
        CharactersFavoriteViewModel(
            characterFavoriteRepository = characterFavoriteRepository
        )
    }

    @Provides
    @FeatureScope
    fun providesCharactersFavoriteAdapter() = CharactersFavoriteAdapter()
}
