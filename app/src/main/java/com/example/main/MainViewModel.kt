package com.example.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.R

val NAV_FRAGMENTS_ID = setOf(R.id.characters_list_fragment, R.id.characters_favorites_fragment)

class MainViewModel :ViewModel() {
    private val stateMulteble = MutableLiveData<MainViewState>()

    val stateLiveData : LiveData<MainViewState>
        get() = stateMulteble

    fun navigationControllerChanged(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (NAV_FRAGMENTS_ID.contains(destination.id)) {
                stateMulteble.postValue(MainViewState.NavigationScreen)
            } else {
                stateMulteble.postValue(MainViewState.FullScreen)
            }
        }
    }
}