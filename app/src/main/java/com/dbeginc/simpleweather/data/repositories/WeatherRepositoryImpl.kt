package com.dbeginc.simpleweather.data.repositories

import android.content.Context
import android.util.Log
import com.dbeginc.simpleweather.data.RxThreadProvider
import com.dbeginc.simpleweather.data.datasources.LocalWeatherDataSource
import com.dbeginc.simpleweather.data.datasources.RemoteWeatherDataSource
import com.dbeginc.simpleweather.data.datasources.openweathermap.OpenWeatherMapRemoteSource
import com.dbeginc.simpleweather.data.datasources.room.RoomDataSource
import com.dbeginc.simpleweather.domain.ThreadProvider
import com.dbeginc.simpleweather.domain.entities.CurrentWeather
import com.dbeginc.simpleweather.domain.entities.CurrentWeatherRequest
import com.dbeginc.simpleweather.domain.repository.WeatherRepository
import io.reactivex.Flowable
import io.reactivex.observers.DisposableCompletableObserver

/**
 * Created by darel on 26.03.18.
 *
 * CurrentWeatherModel Repository
 */
class WeatherRepositoryImpl private constructor(private val local: LocalWeatherDataSource, private val remote: RemoteWeatherDataSource, private val threadProvider: ThreadProvider): WeatherRepository {
    companion object {
        @JvmStatic
        fun create(context: Context) : WeatherRepository {
            val openWeatherSource = OpenWeatherMapRemoteSource.create(context)
            val roomDataSource = RoomDataSource.create(context)

            return WeatherRepositoryImpl(
                    local = roomDataSource,
                    remote = openWeatherSource,
                    threadProvider = RxThreadProvider
            )
        }
    }

    override fun getCurrentWeather(request: CurrentWeatherRequest): Flowable<CurrentWeather> {
        return local.getCurrentWeather(request)
                .subscribeOn(threadProvider.CP)
                .doOnSubscribe {
                    // Fetch from network and update database
                    remote.getCurrentWeather(request)
                            .subscribeOn(threadProvider.IO)
                            .flatMapCompletable { freshData ->
                                local.updateCurrentWeather(freshData)
                                        .subscribeOn(threadProvider.CP)
                            }
                            .subscribe(UpdateObserver())
                }
    }

    private inner class UpdateObserver : DisposableCompletableObserver() {
        override fun onComplete() {
            Log.i("simpleweather", "data updated")
        }

        override fun onError(e: Throwable) {
            Log.e("simpleweather", e.localizedMessage, e)
        }
    }

}