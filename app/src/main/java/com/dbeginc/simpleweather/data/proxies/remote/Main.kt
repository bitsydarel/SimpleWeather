package com.dbeginc.simpleweather.data.proxies.remote

import com.google.gson.annotations.SerializedName

data class Main(@SerializedName("temp")
                val temp: Double,
                @SerializedName("temp_min")
                val tempMin: Int,
                @SerializedName("humidity")
                val humidity: Double? = 0.0,
                @SerializedName("pressure")
                val pressure: Double? = 0.0,
                @SerializedName("temp_max")
                val tempMax: Int)