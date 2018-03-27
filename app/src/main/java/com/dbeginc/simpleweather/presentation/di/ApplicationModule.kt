package com.dbeginc.simpleweather.presentation.di

import android.content.Context
import com.dbeginc.simpleweather.presentation.utils.DataSync
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by darel on 26.03.18.
 *
 * Application Module contain object that need to be
 * available during the full lifecycle of the application
 */
@Module abstract class ApplicationModule {
    @ApplicationScope
    @Binds
    abstract fun provideApplicationContext(application: DaggerApplication): Context

    @ContributesAndroidInjector()
    abstract fun contributeDataSync() : DataSync
}