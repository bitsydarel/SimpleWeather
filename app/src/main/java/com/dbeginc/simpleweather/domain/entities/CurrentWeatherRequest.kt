package com.dbeginc.simpleweather.domain.entities

/**
 * Created by darel on 26.03.18.
 *
 * CurrentWeather Request contain information required to retrieve weather info
 */
data class CurrentWeatherRequest(val locationId: Long, val language: String = "ru", val units: String = "metric")