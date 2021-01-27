package com.example.main.di

import androidx.annotation.VisibleForTesting
import com.example.datasource.di.scope.FeatureScope
import com.example.main.MainFragment
import com.example.main.MainViewModel
import dagger.Component
import dagger.Module
import dagger.Provides

@Module
class MainModule(
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val fragment: MainFragment
) {
    @Provides
    fun providesMainViewModel() : MainViewModel{
        fragment.viewModel = MainViewModel()
        return fragment.viewModel
    }
}
