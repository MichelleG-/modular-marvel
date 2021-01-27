package com.example.main

import com.example.commons.ui.base.BaseViewState

sealed class MainViewState : BaseViewState {

    object FullScreen : MainViewState()

    object NavigationScreen : MainViewState()

    fun isFullScreen() = this is FullScreen

    fun isNavigationScreen() = this is NavigationScreen
}
