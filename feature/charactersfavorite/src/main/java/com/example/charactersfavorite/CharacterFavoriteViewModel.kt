package com.example.charactersfavorite

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datasource.database.characters.favorite.CharacterFavorite
import com.example.datasource.database.characters.favorite.CharacterFavoriteRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersFavoriteViewModel @Inject constructor(
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val characterFavoriteRepository: CharacterFavoriteRepository
) : ViewModel() {

    val data = characterFavoriteRepository.getAllCharactersFavoriteLiveData()
    val state = Transformations.map(data) {
        if (it.isEmpty()) {
            CharactersFavoriteViewState.Empty
        } else {
            CharactersFavoriteViewState.Listed
        }
    }

    fun removeFavoriteCharacter(character: CharacterFavorite) {
        viewModelScope.launch {
            characterFavoriteRepository.deleteCharacterFavorite(character)
        }
    }
}