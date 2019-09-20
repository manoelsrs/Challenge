package com.manoelsrs.marvelchallenge.repository.local.favorite.mappers

import com.manoelsrs.marvelchallenge.model.Character
import com.manoelsrs.marvelchallenge.repository.local.favorite.entity.FavoriteDto

object FavoriteMapper {

    fun toCharacter(dto: FavoriteDto): Character {
        return Character(
            dto.id,
            dto.name,
            dto.description,
            dto.photo,
            dto.photoExtension,
            dto.hasComics,
            dto.hasSeries
        )
    }

    fun toFavoriteDto(character: Character): FavoriteDto {
        return FavoriteDto(
            character.id,
            character.name,
            character.description,
            character.photo,
            character.photoExtension,
            character.hasComics,
            character.hasSeries
        )
    }
}