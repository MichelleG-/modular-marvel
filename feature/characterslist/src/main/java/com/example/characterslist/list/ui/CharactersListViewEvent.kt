package com.example.characterslist.list.ui

sealed class CharactersListViewEvent {

    data class OpenCharacterDetail(val id: Long) : CharactersListViewEvent()
}