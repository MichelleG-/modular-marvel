package com.example.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.datasource.BuildConfig
import com.example.datasource.database.characters.favorite.CharacterFavorite
import com.example.datasource.database.characters.favorite.CharacterFavoriteDao

@Database(
    entities = [CharacterFavorite::class],
    exportSchema = BuildConfig.MARVEL_DATABASE_EXPORT_SCHEMA,
    version = BuildConfig.MARVEL_DATABASE_VERSION
)
abstract class ModularDatabase : RoomDatabase() {

    abstract fun characterFavoriteDao(): CharacterFavoriteDao
}