package com.dbeginc.simpleweather.data.proxies.remote

import com.google.gson.annotations.SerializedName

data class Wind(@SerializedName("deg")
                val deg: Double = 0.0,
                @SerializedName("speed")
                val speed: Double = 0.0
)