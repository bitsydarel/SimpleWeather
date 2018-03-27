package com.dbeginc.simpleweather.presentation.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication

/**
 * Created by darel on 26.03.18.
 *
 * Application component
 */
@ApplicationScope
@Component(modules = [
    ApplicationModule::class, DataModule::class,
    FragmentsModule::class, ViewModelModule::class,
    AndroidInjectionModule::class, AndroidSupportInjectionModule::class
])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {
    @dagger.Component.Builder
    abstract class Builder : AndroidInjector.Builder<DaggerApplication>()
}