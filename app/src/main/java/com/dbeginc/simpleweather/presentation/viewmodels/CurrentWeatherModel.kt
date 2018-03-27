package com.dbeginc.simpleweather.presentation.viewmodels

/**
 * Created by darel on 26.03.18.
 *
 * Current weather
 */
data class CurrentWeatherModel (val location: String,
                                val icon: String,
                                val summary: String,
                                val temperature: String,
                                val temperatureMin: String,
                                val temperatureMax: String
)