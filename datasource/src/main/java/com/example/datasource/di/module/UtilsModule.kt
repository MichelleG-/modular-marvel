package com.example.datasource.di.modules

import com.example.datasource.di.util.ThemeUtils
import com.example.datasource.di.util.ThemeUtilsImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class UtilsModule {

    @Singleton
    @Binds
    abstract fun bindThemeUtils(themeUtilsImpl: ThemeUtilsImpl): ThemeUtils
}
