package com.dbeginc.simpleweather.data.datasources.room

import android.content.Context
import com.dbeginc.simpleweather.data.datasources.LocalWeatherDataSource
import com.dbeginc.simpleweather.data.proxies.local.toDomain
import com.dbeginc.simpleweather.data.proxies.local.toLocal
import com.dbeginc.simpleweather.domain.entities.CurrentWeather
import com.dbeginc.simpleweather.domain.entities.CurrentWeatherRequest
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by darel on 26.03.18.
 *
 * Room from architecture component libraries datasource,
 * it's simply a high level abstraction for sqlite
 */
class RoomDataSource private constructor(private val db: SimpleWeatherDatabase) : LocalWeatherDataSource {

    companion object {
        @JvmStatic fun create(context: Context) : RoomDataSource =
                RoomDataSource(db = SimpleWeatherDatabase.create(context))
    }

    override fun getCurrentWeather(request: CurrentWeatherRequest): Flowable<CurrentWeather> {
        return db.dao().getWeatherById(id = request.locationId)
                .map { localEntity -> localEntity.toDomain() }
    }

    override fun updateCurrentWeather(currentWeather: CurrentWeather): Completable {
        return Completable.fromCallable {
            db.dao().updateOrAddWeather(currentWeather.toLocal())
        }
    }

}