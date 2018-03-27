package com.dbeginc.simpleweather.data.proxies.remote

import com.google.gson.annotations.SerializedName

data class Main(@SerializedName("temp")
                val temp: Double = 0.0,
                @SerializedName("temp_min")
                val tempMin: Int = 0,
                @SerializedName("humidity")
                val humidity: Int = 0,
                @SerializedName("pressure")
                val pressure: Int = 0,
                @SerializedName("temp_max")
                val tempMax: Int = 0)