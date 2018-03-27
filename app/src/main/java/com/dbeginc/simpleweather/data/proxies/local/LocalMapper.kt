package com.dbeginc.simpleweather.data.proxies.local

import com.dbeginc.simpleweather.domain.entities.CurrentWeather

/**
 * Created by darel on 27.03.18.
 *
 * Local Mapper
 */
fun LocalCurrentWeather.toDomain() : CurrentWeather = CurrentWeather(
        latitude = latitude,
        longitude = longitude,
        icon = icon,
        summary = summary,
        location = location,
        locationId = locationId,
        temperature = temperature,
        minTemperature = minTemperature,
        maxTemperature = maxTemperature
)

fun CurrentWeather.toLocal() : LocalCurrentWeather = LocalCurrentWeather(
        latitude = latitude,
        longitude = longitude,
        icon = icon,
        summary = summary,
        location = location,
        locationId = locationId,
        temperature = temperature,
        minTemperature = minTemperature,
        maxTemperature = maxTemperature
)