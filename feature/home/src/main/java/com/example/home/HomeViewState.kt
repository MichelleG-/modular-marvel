package com.example.home

import com.example.commons.ui.base.BaseViewState

/**
 * Different states for [HomeFragment].
 * @see BaseViewState
 */
sealed class HomeViewState : BaseViewState {

    object FullScreen : HomeViewState()

    object NavigationScreen : HomeViewState()

    fun isFullScreen() = this is FullScreen

    fun isNavigationScreen() = this is NavigationScreen
}
