package com.dbeginc.simpleweather.presentation.viewmodels

import android.databinding.ObservableField
import android.databinding.ObservableInt

/**
 * Created by darel on 26.03.18.
 *
 * Current weather
 */
data class CurrentWeather (val location: ObservableField<String>,
                           val icon: ObservableField<String>,
                           val summary: ObservableField<String>,
                           val temperature: ObservableInt,
                           val temperatureMin: ObservableInt,
                           val temperatureMax: ObservableInt
)