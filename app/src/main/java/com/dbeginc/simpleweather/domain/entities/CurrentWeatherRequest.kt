package com.dbeginc.simpleweather.domain.entities

/**
 * Created by darel on 26.03.18.
 *
 * Weather Request contain information required to retrieve weather info
 */
data class WeatherRequest(val locationId: Long, val language: String = "ru", val units: String = "metric")