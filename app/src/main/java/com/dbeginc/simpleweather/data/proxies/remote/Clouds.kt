package com.dbeginc.simpleweather.data.proxies.remote

import com.google.gson.annotations.SerializedName

data class Clouds(@SerializedName("all") val all: Int = 0)