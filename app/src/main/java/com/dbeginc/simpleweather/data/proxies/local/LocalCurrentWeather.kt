package com.dbeginc.simpleweather.data.proxies.local

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by darel on 26.03.18.
 *
 * Local Weather Entity
 */
@Entity(tableName = "weather")
data class LocalCurrentWeather (
        val latitude: Double,
        val longitude: Double,
        val icon: String,
        val summary: String,
        val location: String,
        @PrimaryKey @ColumnInfo(name = "location_id") val locationId: Long,
        val temperature: Double,
        @ColumnInfo(name = "min_temperature") val minTemperature: Int,
        @ColumnInfo(name = "max_temperature") val maxTemperature: Int
)