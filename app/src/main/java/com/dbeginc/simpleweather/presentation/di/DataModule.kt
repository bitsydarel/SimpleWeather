package com.dbeginc.simpleweather.presentation.di

import android.content.Context
import android.content.SharedPreferences
import com.dbeginc.simpleweather.data.RxThreadProvider
import com.dbeginc.simpleweather.data.repositories.WeatherRepositoryImpl
import com.dbeginc.simpleweather.domain.ThreadProvider
import com.dbeginc.simpleweather.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides

/**
 * Created by darel on 26.03.18.
 *
 * Data Provider Module
 */
@Module
class DataModule {
    @Provides
    @ApplicationScope
    fun provideUserRepository(appContext: Context) : WeatherRepository =
            WeatherRepositoryImpl.create(appContext)

    @Provides
    @ApplicationScope
    fun provideSharedPreferences(appContext: Context) : SharedPreferences =
            appContext.getSharedPreferences("simple_weather_prefs", Context.MODE_PRIVATE)

    @Provides
    @ApplicationScope
    fun provideThreadProvider() : ThreadProvider = RxThreadProvider

}