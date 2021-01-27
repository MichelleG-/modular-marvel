package com.example.characterslist.list.pagging

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.characterslist.list.model.CharacterItem
import com.example.characterslist.list.model.CharacterItemMapper
import com.example.datasource.di.annotations.OpenForTesting
import com.example.datasource.di.network.NetworkState
import com.example.datasource.di.network.repositorys.CharactersRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

const val PAGE_INIT_ELEMENTS = 0
const val PAGE_MAX_ELEMENTS = 50

@OpenForTesting
class CharactersPageDataSource @Inject constructor(
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val repository: CharactersRepository,
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val scope: CoroutineScope,
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val mapper: CharacterItemMapper
) : PageKeyedDataSource<Int, CharacterItem>() {

    val networkState = MutableLiveData<NetworkState>()
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var retry: (() -> Unit)? = null

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CharacterItem>
    ) {
        networkState.postValue(NetworkState.Loading())
        scope.launch(
            CoroutineExceptionHandler { _, _ ->
                retry = {
                    loadInitial(params, callback)
                }
                networkState.postValue(NetworkState.Error())
            }
        ) {
            val response = repository.getCharacters(
                offset = PAGE_INIT_ELEMENTS,
                limit = PAGE_MAX_ELEMENTS
            )
            val data = mapper.map(response)
            callback.onResult(data, null, PAGE_MAX_ELEMENTS)
            networkState.postValue(NetworkState.Success(isEmptyResponse = data.isEmpty()))
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CharacterItem>
    ) {
        networkState.postValue(NetworkState.Loading(true))
        scope.launch(
            CoroutineExceptionHandler { _, _ ->
                retry = {
                    loadAfter(params, callback)
                }
                networkState.postValue(NetworkState.Error(true))
            }
        ) {
            val response = repository.getCharacters(
                offset = params.key,
                limit = PAGE_MAX_ELEMENTS
            )
            val data = mapper.map(response)
            callback.onResult(data, params.key + PAGE_MAX_ELEMENTS)
            networkState.postValue(NetworkState.Success(true, data.isEmpty()))
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CharacterItem>
    ) {
        // Ignored, since we only ever append to our initial load
    }

    fun retry() {
        retry?.invoke()
    }
}
