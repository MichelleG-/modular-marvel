package com.example.datasource.di

import android.content.Context
import com.example.datasource.database.characters.favorite.CharacterFavoriteDao
import com.example.datasource.di.modules.ContextModule
import com.example.datasource.di.modules.DatabaseModule
import com.example.datasource.di.modules.NetworkModule
import com.example.datasource.di.modules.UtilsModule
import com.example.datasource.di.network.repositorys.CharactersRepository
import com.example.datasource.di.network.service.CharacterService
import com.example.datasource.di.util.ThemeUtils
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            ContextModule::class,
            NetworkModule::class,
            DatabaseModule::class,
            UtilsModule::class
        ]
)
interface DataSourceComponent {

    fun context(): Context

    fun marvelService(): CharacterService

    fun marvelRepository(): CharactersRepository

    fun characterFavoriteDao(): CharacterFavoriteDao

    fun themeUtils(): ThemeUtils
}