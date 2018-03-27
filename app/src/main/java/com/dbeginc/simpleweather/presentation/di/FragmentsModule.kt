package com.dbeginc.simpleweather.presentation.di

import com.dbeginc.simpleweather.presentation.settings.SettingsFragment
import com.dbeginc.simpleweather.presentation.weather.CurrentWeatherFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by darel on 26.03.18.
 *
 * Fragment module
 */
@Module abstract class FragmentsModule {
    @ContributesAndroidInjector()
    abstract fun contributeCurrentWeatherFragment() : CurrentWeatherFragment

    @ContributesAndroidInjector()
    abstract fun contributeCurrentSettingsFragment() : SettingsFragment
}