package com.dbeginc.simpleweather.data.datasources.openweathermap

import android.content.Context
import android.support.annotation.VisibleForTesting
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.interceptors.HttpLoggingInterceptor
import com.dbeginc.simpleweather.BuildConfig
import com.dbeginc.simpleweather.data.datasources.RemoteWeatherDataSource
import com.dbeginc.simpleweather.data.proxies.remote.OpenWeather
import com.dbeginc.simpleweather.data.proxies.remote.toDomain
import com.dbeginc.simpleweather.domain.entities.CurrentWeather
import com.dbeginc.simpleweather.domain.entities.CurrentWeatherRequest
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.jetbrains.annotations.TestOnly
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by darel on 26.03.18.
 *
 * Open Weather Map remote source
 */
class OpenWeatherMapRemoteSource private constructor(private val openWeatherMapUrl: String): RemoteWeatherDataSource {

    companion object {
        private const val OPEN_WEATHER_MAP_NETWORK_CACHE : Long = 50 * 1024 * 1024 // 50 MB

        @JvmStatic
        fun create(context: Context) : OpenWeatherMapRemoteSource {
            val appContext = context.applicationContext

            val client = OkHttpClient.Builder()
                    .connectTimeout(35, TimeUnit.SECONDS)
                    .writeTimeout(35, TimeUnit.SECONDS)
                    .readTimeout(55, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .cache(Cache(
                            File(appContext.cacheDir, "openweathermap_cache"),
                            OPEN_WEATHER_MAP_NETWORK_CACHE
                    ))
                    .build()

            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BASIC)
            AndroidNetworking.initialize(context, client)

            return OpenWeatherMapRemoteSource(openWeatherMapUrl = "http://api.openweathermap.org")
        }

        @TestOnly
        @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
        @JvmStatic
        fun create(url: String) : OpenWeatherMapRemoteSource =
                OpenWeatherMapRemoteSource(openWeatherMapUrl = url)

    }

    override fun getCurrentWeather(request: CurrentWeatherRequest): Single<CurrentWeather> {
        return Rx2AndroidNetworking
                .get("$openWeatherMapUrl/data/2.5/weather")
                .addQueryParameter("id", request.locationId.toString())
                .addQueryParameter("units", request.units)
                .addQueryParameter("lang", request.language)
                .addQueryParameter("APPID", BuildConfig.OPEN_WEATHER_MAP_API_KEY)
                .build()
                .getObjectSingle(OpenWeather::class.java)
                .map { openWeather -> openWeather.toDomain() }
    }

}