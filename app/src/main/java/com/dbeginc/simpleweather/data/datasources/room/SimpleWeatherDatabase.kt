package com.dbeginc.simpleweather.data.datasources.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.dbeginc.simpleweather.data.proxies.local.LocalCurrentWeather

/**
 * Created by darel on 26.03.18.
 *
 * SimpleWeather Database
 */
@Database(entities = [LocalCurrentWeather::class], version = 1, exportSchema = true)
abstract class SimpleWeatherDatabase : RoomDatabase() {
    abstract fun dao() : SimpleWeatherDao

    companion object {
        @JvmStatic fun create(context: Context) : SimpleWeatherDatabase {
            return Room.databaseBuilder(
                    context,
                    SimpleWeatherDatabase::class.java,
                    "simple_weather_db"
            ).build()
        }
    }
}