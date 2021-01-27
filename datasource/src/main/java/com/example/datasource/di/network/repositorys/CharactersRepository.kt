package com.example.datasource.di.network.repositorys

import androidx.annotation.VisibleForTesting
import com.example.datasource.BuildConfig
import com.example.datasource.di.extensions.toMD5
import com.example.datasource.di.network.response.BaseResponse
import com.example.datasource.di.network.response.character.CharacterResponse
import com.example.datasource.di.network.service.CharacterService

private const val API_PUBLIC_KEY = BuildConfig.MARVEL_API_KEY_PUBLIC
private const val API_PRIVATE_KEY = BuildConfig.MARVEL_API_KEY_PRIVATE
private const val HASH_FORMAT = "%s%s%s"


class CharactersRepository(
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val service: CharacterService
) {

    suspend fun getCharacter(id: Long): BaseResponse<CharacterResponse> {
        return service.getCharacter(
            id = id,
            apiKey = API_PUBLIC_KEY,
            hash = generateApiHash(generateTimestamp()),
            timestamp = generateTimestamp()
        )
    }

    suspend fun getCharacters(offset: Int, limit: Int): BaseResponse<CharacterResponse> {

        return service.getCharacters(
            apiKey = API_PUBLIC_KEY,
            hash = generateApiHash(generateTimestamp()),
            timestamp = generateTimestamp(),
            offset = offset,
            limit = limit
        )
    }

    internal fun generateApiHash(timestamp: String) =
        "%s%s%s".format(timestamp, BuildConfig.MARVEL_API_KEY_PRIVATE, BuildConfig.MARVEL_API_KEY_PUBLIC).toMD5()


    internal fun generateTimestamp() =
        (System.currentTimeMillis()/1000).toString()
}
