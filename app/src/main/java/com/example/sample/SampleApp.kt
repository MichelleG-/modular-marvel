package com.example.sample

import android.content.Context
import com.example.BuildConfig
import com.example.datasource.di.DaggerDataSourceComponent

import com.example.datasource.di.DataSourceComponent
import com.example.datasource.di.modules.ContextModule
import com.example.datasource.di.util.ThemeUtils
import com.example.sample.di.DaggerAppComponent
import com.google.android.play.core.splitcompat.SplitCompatApplication
import javax.inject.Inject
import kotlin.random.Random

class SampleApp : SplitCompatApplication() {

    lateinit var dataSourceComponent: DataSourceComponent

    @Inject
    lateinit var themeUtils: ThemeUtils

    companion object {
        @JvmStatic
        fun dataSourceComponent(context: Context) =
            (context.applicationContext as? SampleApp)?.dataSourceComponent
    }

    override fun onCreate() {
        super.onCreate()
        initCoreDependencyInjection()
        initAppDependencyInjection()
        initRandomNightMode()
    }

    private fun initAppDependencyInjection() {
        DaggerAppComponent
            .builder()
            .dataSourceComponent(dataSourceComponent)
            .build()
            .inject(this)
    }

    private fun initCoreDependencyInjection() {
        dataSourceComponent = DaggerDataSourceComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }

    private fun initRandomNightMode() {
        if (BuildConfig.DEBUG) {
            themeUtils.setNightMode(Random.nextBoolean())
        }
    }
}
