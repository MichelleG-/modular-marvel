package com.example.datasource.di.modules

import android.content.Context
import androidx.room.Room
import com.example.datasource.BuildConfig
import com.example.datasource.database.ModularDatabase
import com.example.datasource.database.characters.favorite.CharacterFavoriteDao
import com.example.datasource.database.characters.favorite.CharacterFavoriteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideMarvelDatabase(context: Context) =
        Room.databaseBuilder(
            context,
            ModularDatabase::class.java,
            BuildConfig.MARVEL_DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideCharacterFavoriteDao(marvelDatabase: ModularDatabase) =
        marvelDatabase.characterFavoriteDao()

    @Singleton
    @Provides
    fun provideCharacterFavoriteRepository(
        characterFavoriteDao: CharacterFavoriteDao
    ) = CharacterFavoriteRepository(characterFavoriteDao)
}
