package com.example.characterslist.detail.di

import com.example.characterslist.detail.ui.CharacterDetailFragment
import com.example.datasource.di.DataSourceComponent
import com.example.datasource.di.scope.FeatureScope
import dagger.Component

@FeatureScope
@Component(
    modules = [CharacterDetailModule::class],
    dependencies = [DataSourceComponent::class]
)
interface CharacterDetailComponent {
    fun inject(detailFragment: CharacterDetailFragment)
}
