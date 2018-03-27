package com.dbeginc.simpleweather.data.datasources

import com.dbeginc.simpleweather.domain.entities.CurrentWeather
import com.dbeginc.simpleweather.domain.entities.CurrentWeatherRequest
import io.reactivex.Single

/**
 * Created by darel on 26.03.18.
 *
 * WeatherDataSource represent a source of data that can be memory , offline or network
 * every data source must implementation this interface
 *
 * This allow us to easily switch sources without changing most of our code
 *
 * If in the future we have method only required for local or remote sources
 * we will create other interfaces as localDataSource or RemoteDataSource
 */
interface WeatherDataSource {
    /**
     * Get weather from remote data source
     * @param request containing all information the remote source
     * need to complete the task
     */
    fun getCurrentWeather(request: CurrentWeatherRequest) : Single<CurrentWeather>
}