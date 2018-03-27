package com.dbeginc.simpleweather.data.datasources.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.dbeginc.simpleweather.data.proxies.local.LocalCurrentWeather
import io.reactivex.Flowable

/**
 * Created by darel on 26.03.18.
 *
 * SimpleWeather dao
 */
@Dao
interface SimpleWeatherDao {
    /**
     * Query the database for weather id matching the following id
     *
     * I strongly typed the table name because since it's a demo
     * i don't want to create constant holder classes ect.
     */
    @Query("SELECT * FROM weather WHERE location_id LIKE :id")
    fun getWeatherById(id: Long) : Flowable<LocalCurrentWeather>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateOrAddWeather(localCurrentWeather: LocalCurrentWeather)
}