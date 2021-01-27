package com.example.characterslist.list.model

import com.example.datasource.di.mapper.Mapper
import com.example.datasource.di.network.response.BaseResponse
import com.example.datasource.di.network.response.character.CharacterResponse

private const val IMAGE_URL_FORMAT = "%s.%s"

class CharacterItemMapper : Mapper<BaseResponse<CharacterResponse>, List<CharacterItem>> {

    override suspend fun map(from: BaseResponse<CharacterResponse>) =
        from.data.results.map {
            CharacterItem(
                id = it.id,
                name = it.name,
                description = it.description,
                imageUrl = IMAGE_URL_FORMAT.format(
                    it.thumbnail.path.replace("http", "https"),
                    it.thumbnail.extension
                )
            )
        }
}
