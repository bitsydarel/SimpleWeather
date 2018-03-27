package com.dbeginc.simpleweather.presentation.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.dbeginc.simpleweather.presentation.base.SimpleWeatherViewModelFactory
import com.dbeginc.simpleweather.presentation.viewmodels.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by darel on 26.03.18.
 *
 * ViewModel Module create dependency graph for ViewModels
 */
@Module
abstract class ViewModelModule {
    @Binds // Binds all dependencies needed
    @IntoMap // Add this viewModel into the map that will be passed the viewModelProvider
    @ViewModelKey(WeatherViewModel::class) // value of element as this
    abstract fun bindWeatherViewModel(WeatherViewModel: WeatherViewModel): ViewModel

    @ApplicationScope
    @Binds
    abstract fun bindViewModelFactory(factory: SimpleWeatherViewModelFactory): ViewModelProvider.Factory

}