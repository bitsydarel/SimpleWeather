package com.dbeginc.simpleweather.data.datasources

import com.dbeginc.simpleweather.domain.entities.CurrentWeather
import com.dbeginc.simpleweather.domain.entities.CurrentWeatherRequest
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by darel on 26.03.18.
 *
 * LocalWeatherDataSource represent a source of data that can be memory , offline
 * every data source that are kept on device must implementation this interface
 *
 * This allow us to easily switch sources without changing most of our code
 */
interface LocalWeatherDataSource {
    /**
     * Get weather from remote data source
     * @param request containing all information the remote source
     * need to complete the task
     */
    fun getCurrentWeather(request: CurrentWeatherRequest) : Flowable<CurrentWeather>

    fun updateCurrentWeather(currentWeather: CurrentWeather) : Completable
}
