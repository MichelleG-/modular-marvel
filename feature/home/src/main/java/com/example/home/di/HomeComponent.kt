package com.example.home.di

import com.example.datasource.di.DataSourceComponent
import com.example.datasource.di.scope.FeatureScope
import com.example.home.HomeFragment
import dagger.Component

/**
 * Class for which a fully-formed, dependency-injected implementation is to
 * be generated from [HomeModule].
 * @see Component
 */
@FeatureScope
@Component(
    modules = [HomeModule::class],
    dependencies = [DataSourceComponent::class]
)
interface HomeComponent {
    fun inject(homeFragment: HomeFragment)
}
