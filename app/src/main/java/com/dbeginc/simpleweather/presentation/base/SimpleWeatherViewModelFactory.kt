package com.dbeginc.simpleweather.presentation.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.dbeginc.simpleweather.presentation.di.ApplicationScope
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by darel on 26.03.18.
 *
 * View Model factory
 *
 * Check Factory pattern, generally this class is used has helper to provide different viewModel type
 */
@ApplicationScope
class SimpleWeatherViewModelFactory  @Inject constructor(private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator = creators[modelClass]

        if (creator == null) {

            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }

        if (creator == null) {
            throw IllegalArgumentException("Unknown model class $modelClass")
        }

        try {
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}