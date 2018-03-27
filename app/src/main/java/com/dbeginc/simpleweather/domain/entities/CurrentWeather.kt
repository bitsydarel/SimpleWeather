package com.dbeginc.simpleweather.domain.entities

/**
 * Created by darel on 26.03.18.
 *
 * Weather class representing Weather information
 */
data class Weather(
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