package com.example.sample.di

import com.example.sample.SampleApp
import com.example.datasource.di.DataSourceComponent
import com.example.datasource.di.scope.AppScope
import dagger.Component

@AppScope
@Component(
    dependencies = [DataSourceComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {
    fun inject(application: SampleApp)
}
