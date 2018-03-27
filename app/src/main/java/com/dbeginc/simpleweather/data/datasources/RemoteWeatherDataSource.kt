package com.dbeginc.simpleweather.data.datasources

import com.dbeginc.simpleweather.domain.entities.CurrentWeather
import com.dbeginc.simpleweather.domain.entities.CurrentWeatherRequest
import io.reactivex.Single

/**
 * Created by darel on 26.03.18.
 *
 * RemoteWeatherDataSource represent a source of data from network
 * every weather data source connected to network must implementation this interface
 *
 * This allow us to easily switch sources in the future without changing most of our code
 * and it's more testable
 */
interface RemoteWeatherDataSource {
    /**
     * Get weather from remote data source
     * @param request containing all information the remote source
     * need to complete the task
     */
    fun getCurrentWeather(request: CurrentWeatherRequest) : Single<CurrentWeather>
}