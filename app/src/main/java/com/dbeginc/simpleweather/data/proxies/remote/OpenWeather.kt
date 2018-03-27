package com.dbeginc.simpleweather.data.proxies.remote

import com.google.gson.annotations.SerializedName

data class OpenWeather(@SerializedName("dt")
                       val dt: Int?,
                       @SerializedName("coord")
                       val coord: Coord,
                       @SerializedName("visibility")
                       val visibility: Int?,
                       @SerializedName("weather")
                       val weather: List<WeatherItem>,
                       @SerializedName("name")
                       val name: String = "",
                       @SerializedName("cod")
                       val cod: Int?,
                       @SerializedName("main")
                       val main: Main,
                       @SerializedName("clouds")
                       val clouds: Clouds?,
                       @SerializedName("id")
                       val id: Long,
                       @SerializedName("sys")
                       val sys: Sys,
                       @SerializedName("base")
                       val base: String = "",
                       @SerializedName("wind")
                       val wind: Wind?)