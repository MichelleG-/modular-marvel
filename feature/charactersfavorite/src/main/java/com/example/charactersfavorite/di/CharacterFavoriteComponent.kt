package com.example.charactersfavorite.di

import com.example.charactersfavorite.CharacterFavoriteFragment
import com.example.datasource.di.DataSourceComponent
import com.example.datasource.di.scope.FeatureScope
import dagger.Component

@FeatureScope
@Component(
    modules = [CharacterFavoriteModule::class],
    dependencies = [DataSourceComponent::class]
)
interface CharacterFavoriteComponent {
    fun inject(favoriteFragment: CharacterFavoriteFragment)
}

