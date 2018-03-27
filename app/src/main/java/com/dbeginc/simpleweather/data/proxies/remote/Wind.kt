package com.dbeginc.simpleweather.data.proxies.remote

import com.google.gson.annotations.SerializedName

data class Wind(@SerializedName("deg")
                val deg: Int = 0,
                @SerializedName("speed")
                val speed: Int = 0)