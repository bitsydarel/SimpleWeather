package com.dbeginc.simpleweather.domain.entities

/**
 * Created by darel on 26.03.18.
 *
 * CurrentWeatherModel class representing CurrentWeatherModel information
 */
data class CurrentWeather(
        val latitude: Double,
        val longitude: Double,
        val icon: String,
        val summary: String,
        val location: String,
        val locationId: Long,
        val temperature: Double,
        val minTemperature: Int,
        val maxTemperature: Int
)