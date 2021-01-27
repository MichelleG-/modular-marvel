package com.example.characterslist.list.ui

import com.example.commons.ui.base.BaseViewState

sealed class CharactersListViewState : BaseViewState {

    object Refreshing : CharactersListViewState()

    object Loaded : CharactersListViewState()

    object Loading : CharactersListViewState()

    object AddLoading : CharactersListViewState()

    object Empty : CharactersListViewState()

    object Error : CharactersListViewState()

    object AddError : CharactersListViewState()

    object NoMoreElements : CharactersListViewState()

    fun isRefreshing() = this is Refreshing

    fun isLoaded() = this is Loaded

    fun isLoading() = this is Loading

    fun isAddLoading() = this is AddLoading

    fun isEmpty() = this is Empty

    fun isError() = this is Error

    fun isAddError() = this is AddError

    fun isNoMoreElements() = this is NoMoreElements
}