package com.example.charactersfavorite

import com.example.commons.ui.base.BaseViewState

sealed class CharactersFavoriteViewState : BaseViewState {
    object Empty : CharactersFavoriteViewState()

    object Listed : CharactersFavoriteViewState()

    fun isEmpty() = this is Empty

    fun isListed() = this is Listed
}