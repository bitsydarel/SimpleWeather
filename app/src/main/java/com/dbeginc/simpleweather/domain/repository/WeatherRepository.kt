package com.dbeginc.simpleweather.domain.repository

import com.dbeginc.simpleweather.domain.entities.CurrentWeather
import com.dbeginc.simpleweather.domain.entities.CurrentWeatherRequest
import io.reactivex.Flowable

/**
 * Created by darel on 26.03.18.
 *
 * CurrentWeather Repository
 */
interface WeatherRepository {
    fun getCurrentWeather(request: CurrentWeatherRequest) : Flowable<CurrentWeather>
}