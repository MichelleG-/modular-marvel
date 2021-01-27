package com.example.characterslist.list.pagging

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.characterslist.list.model.CharacterItem
import javax.inject.Inject
import javax.inject.Provider


class CharactersPageDataSourceFactory
@Inject constructor(
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val providerDataSource: Provider<CharactersPageDataSource>
) : DataSource.Factory<Int, CharacterItem>() {

    var sourceLiveData = MutableLiveData<CharactersPageDataSource>()

    override fun create(): DataSource<Int, CharacterItem> {
        val dataSource = providerDataSource.get()
        sourceLiveData.postValue(dataSource)
        return dataSource
    }

    fun refresh() {
        sourceLiveData.value?.invalidate()
    }

    fun retry() {
        sourceLiveData.value?.retry()
    }
}
