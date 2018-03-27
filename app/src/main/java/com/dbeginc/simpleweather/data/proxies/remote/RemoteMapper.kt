package com.dbeginc.simpleweather.data.proxies.remote

import com.dbeginc.simpleweather.domain.entities.CurrentWeather

/**
 * Created by darel on 26.03.18.
 *
 * mapper remote OpenWeather object to domain objects
 *
 *
 */
fun OpenWeather.toDomain() : CurrentWeather {
    /* Not test necessary for this call since the current
     * Open CurrentWeatherModel api only return one item in the list
     * if not object then we assume  that we have invalid data
     */
    val info = weather.first()

    return CurrentWeather(
            latitude = coord.lat,
            longitude = coord.lon,
            icon = info.icon,
            summary = info.description,
            temperature = main.temp,
            location = name,
            locationId = id,
            minTemperature = main.tempMin,
            maxTemperature = main.tempMax
    )
}