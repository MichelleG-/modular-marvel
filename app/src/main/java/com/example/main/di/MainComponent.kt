package com.example.main.di

import com.example.datasource.di.DataSourceComponent
import com.example.datasource.di.scope.FeatureScope
import com.example.main.MainFragment
import dagger.Component

@FeatureScope
@Component(
    modules = [MainModule::class],
    dependencies = [DataSourceComponent::class]
)
interface MainComponent {
    fun inject(mainFragment: MainFragment)
}
