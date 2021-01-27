package com.example.characterslist.list.di

import com.example.characterslist.list.ui.CharactersListFragment
import com.example.datasource.di.DataSourceComponent
import com.example.datasource.di.scope.FeatureScope
import dagger.Component

@FeatureScope
@Component(
    modules = [CharactersListModule::class],
    dependencies = [DataSourceComponent::class]
)
interface CharactersListComponent {

    fun inject(listFragment: CharactersListFragment)
}
