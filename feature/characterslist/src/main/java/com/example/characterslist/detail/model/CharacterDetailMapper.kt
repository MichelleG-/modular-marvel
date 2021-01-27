package com.example.characterslist.detail.model

import com.example.datasource.di.mapper.Mapper
import com.example.datasource.di.network.response.BaseResponse
import com.example.datasource.di.network.response.character.CharacterResponse

private const val IMAGE_URL_FORMAT = "%s.%s"

class CharacterDetailMapper : Mapper<BaseResponse<CharacterResponse>, CharacterDetail> {

    @Throws(NoSuchElementException::class)
    override suspend fun map(from: BaseResponse<CharacterResponse>): CharacterDetail {
        val characterResponse = from.data.results.first()
        return CharacterDetail(
            id = characterResponse.id,
            name = characterResponse.name,
            description = characterResponse.description,
            imageUrl = IMAGE_URL_FORMAT.format(
                characterResponse.thumbnail.path.replace("http", "https"),
                characterResponse.thumbnail.extension
            )
        )
    }
}
