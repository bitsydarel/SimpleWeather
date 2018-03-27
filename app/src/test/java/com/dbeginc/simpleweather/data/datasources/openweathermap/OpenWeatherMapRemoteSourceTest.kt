package com.dbeginc.simpleweather.data.datasources.openweathermap

import com.dbeginc.simpleweather.domain.entities.CurrentWeatherRequest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import java.util.*

/**
 * Created by darel on 26.03.18.
 *
 * OpenWeatherMapRemoteSource Test
 */
class OpenWeatherMapRemoteSourceTest {
    private lateinit var api: OpenWeatherMapRemoteSource
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        val mockServer = MockWebServer()

        mockServer.start()

        api = OpenWeatherMapRemoteSource.create(mockServer.url("/").toString())

        server = mockServer

    }

    @Test
    fun getCurrentWeather() {
        val mockResponse = MockResponse().apply {
            setResponseCode(200)
            addHeader("Cache-Control", "no-cache")
            addHeader("Content-Type", "application/json; charset=utf-8")
            setBody(getFileAsString("weather.json"))
        }

        server.enqueue(mockResponse)

        api.getCurrentWeather(request = CurrentWeatherRequest(0) /*Not important */)
                .test()
                .assertValue {
                    it.icon == "04n" &&
                            it.latitude == 50.43 &&
                            it.longitude == 30.52 &&
                            it.location == "Kiev" &&
                            it.locationId == 703448L &&
                            it.temperature == 2.66 &&
                            it.minTemperature == 2 &&
                            it.maxTemperature == 3
                }
    }

    private fun getFileAsString(filename: String) : String {
        val builder = StringBuffer()

        val data = Scanner(Thread.currentThread().contextClassLoader.getResourceAsStream(filename))

        while (data.hasNextLine()) builder.append(data.nextLine())

        return builder.toString()
    }

}