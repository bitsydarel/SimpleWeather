package com.dbeginc.simpleweather.data.proxies.remote

import com.google.gson.annotations.SerializedName

data class OpenWeather(@SerializedName("dt")
                       val dt: Int = 0,
                       @SerializedName("coord")
                       val coord: Coord,
                       @SerializedName("visibility")
                       val visibility: Int = 0,
                       @SerializedName("weather")
                       val weather: List<WeatherItem>?,
                       @SerializedName("name")
                       val name: String = "",
                       @SerializedName("cod")
                       val cod: Int = 0,
                       @SerializedName("main")
                       val main: Main,
                       @SerializedName("clouds")
                       val clouds: Clouds,
                       @SerializedName("id")
                       val id: Int = 0,
                       @SerializedName("sys")
                       val sys: Sys,
                       @SerializedName("base")
                       val base: String = "",
                       @SerializedName("wind")
                       val wind: Wind)