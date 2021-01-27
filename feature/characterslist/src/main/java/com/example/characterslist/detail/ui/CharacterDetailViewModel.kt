package com.example.characterslist.detail.ui

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.characterslist.detail.model.CharacterDetail
import com.example.characterslist.detail.model.CharacterDetailMapper
import com.example.datasource.database.characters.favorite.CharacterFavoriteRepository
import com.example.datasource.di.network.repositorys.CharactersRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailViewModel @Inject constructor(
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val marvelRepository: CharactersRepository,
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val characterFavoriteRepository: CharacterFavoriteRepository,
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val characterDetailMapper: CharacterDetailMapper
) : ViewModel() {

    private val _data = MutableLiveData<CharacterDetail>()
    val data: LiveData<CharacterDetail>
        get() = _data

    private val _state = MutableLiveData<CharacterDetailViewState>()
    val state: LiveData<CharacterDetailViewState>
        get() = _state

    fun loadCharacterDetail(characterId: Long) {
        _state.postValue(CharacterDetailViewState.Loading)
        viewModelScope.launch {
            try {
                val result = marvelRepository.getCharacter(characterId)
                _data.postValue(characterDetailMapper.map(result))

                characterFavoriteRepository.getCharacterFavorite(characterId)?.let {
                    _state.postValue(CharacterDetailViewState.AlreadyAddedToFavorite)
                } ?: run {
                    _state.postValue(CharacterDetailViewState.AddToFavorite)
                }
            } catch (e: Exception) {
                _state.postValue(CharacterDetailViewState.Error)
            }
        }
    }

    fun addCharacterToFavorite() {
        _data.value?.let {
            viewModelScope.launch {
                characterFavoriteRepository.insertCharacterFavorite(
                    id = it.id,
                    name = it.name,
                    imageUrl = it.imageUrl
                )
                _state.postValue(CharacterDetailViewState.AddedToFavorite)
            }
        }
    }

    /**
     * Send interaction event for dismiss character detail view.
     */
    fun dismissCharacterDetail() {
        _state.postValue(CharacterDetailViewState.Dismiss)
    }
}
