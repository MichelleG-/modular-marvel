package com.example.characterslist.list.di

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.viewModelScope
import com.example.characterslist.detail.ui.CharacterDetailViewModel
import com.example.characterslist.list.model.CharacterItemMapper
import com.example.characterslist.list.pagging.CharactersPageDataSource
import com.example.characterslist.list.pagging.CharactersPageDataSourceFactory
import com.example.characterslist.list.ui.CharactersListFragment
import com.example.characterslist.list.ui.CharactersListViewModel
import com.example.characterslist.list.ui.adapter.CharactersListAdapter
import com.example.datasource.di.network.repositorys.CharactersRepository
import com.example.datasource.di.scope.FeatureScope
import dagger.Module
import dagger.Provides

@Module
class CharactersListModule(
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val fragment: CharactersListFragment
) {

    @FeatureScope
    @Provides
    fun providesCharactersListViewModel(
        dataFactory: CharactersPageDataSourceFactory
    ) = fragment.viewModel.apply {
        CharactersListViewModel(
            dataSourceFactory = dataFactory) }

    @Provides
    fun providesCharactersPageDataSource(
        viewModel: CharactersListViewModel,
        repository: CharactersRepository,
        mapper: CharacterItemMapper
    ) = CharactersPageDataSource(
        repository = repository,
        scope = viewModel.viewModelScope,
        mapper = mapper
    )

    @FeatureScope
    @Provides
    fun providesCharacterItemMapper() = CharacterItemMapper()

    @FeatureScope
    @Provides
    fun providesCharactersListAdapter(
        viewModel: CharactersListViewModel
    ) = CharactersListAdapter(viewModel)
}
