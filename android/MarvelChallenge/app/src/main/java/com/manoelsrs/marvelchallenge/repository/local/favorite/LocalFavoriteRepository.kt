package com.manoelsrs.marvelchallenge.repository.local.favorite

import androidx.paging.DataSource
import com.manoelsrs.marvelchallenge.model.Character
import com.manoelsrs.marvelchallenge.repository.local.favorite.entity.FavoriteDatabase
import com.manoelsrs.marvelchallenge.repository.local.favorite.entity.FavoriteDto
import com.manoelsrs.marvelchallenge.repository.local.favorite.resources.LocalFavoriteResources
import io.reactivex.Completable
import io.reactivex.Single

class LocalFavoriteRepository(private val database: FavoriteDatabase) : LocalFavoriteResources {

    override fun getFavorites(): DataSource.Factory<Int, Character> {
        return database.favoriteDao().getFavorites()
            .map { Character(it.id, it.name, it.photo, it.photoExtension) }
    }

    override fun getFavorites(content: String): Single<List<Character>> {
        return database.favoriteDao().getFavoritesSingle()
            .map { favorites ->
                val characters = arrayListOf<Character>()
                favorites.map {
                    if (it.name.contains(content)) characters.add(
                        Character(it.id, it.name, it.photo, it.photoExtension)
                    )
                }
                characters.sortedBy { it.name }
            }
    }

    override fun insert(favorite: Character): Completable = Completable.fromCallable {
        database.favoriteDao().insert(
            FavoriteDto(
                favorite.id,
                favorite.name,
                favorite.photo,
                favorite.photoExtension
            )
        )
    }

    override fun delete(favorite: Character): Completable = Completable.fromCallable {
        database.favoriteDao().delete(
            FavoriteDto(
                favorite.id,
                favorite.name,
                favorite.photo,
                favorite.photoExtension
            )
        )
    }

    override fun deleteAll(): Completable = Completable.fromCallable {
        database.favoriteDao().deleteAll()
    }
}