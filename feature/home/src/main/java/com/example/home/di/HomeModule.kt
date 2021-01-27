package com.example.home.di

import androidx.annotation.VisibleForTesting
import com.example.datasource.di.scope.FeatureScope
import com.example.home.HomeFragment
import com.example.home.HomeViewModel
import dagger.Module
import dagger.Provides

/**
 * Class that contributes to the object graph [HomeComponent].
 * @see Module
 */
@Module
class HomeModule(
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val fragment: HomeFragment

) {
    @Provides
    @FeatureScope
    fun providesHomeViewModel() = fragment.viewModel {
        HomeViewModel()
    }
}

