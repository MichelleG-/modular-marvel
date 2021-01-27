package com.example.sample.di

import android.content.Context
import com.example.sample.SampleApp
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideContext(application: SampleApp): Context = application.applicationContext
}
