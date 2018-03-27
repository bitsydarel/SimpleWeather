package com.dbeginc.simpleweather.presentation.utils

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.SharedPreferences
import android.util.Log
import com.dbeginc.simpleweather.domain.entities.CurrentWeatherRequest
import com.dbeginc.simpleweather.domain.repository.WeatherRepository
import dagger.android.AndroidInjection
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by darel on 27.03.18.
 *
 * Database Sync
 */
class DataSync @Inject constructor() : JobService(){
    @Inject lateinit var weatherRepo: dagger.Lazy<WeatherRepository>
    @Inject lateinit var preferences: dagger.Lazy<SharedPreferences>
    private var task: Disposable? = null

    override fun onCreate() {
        super.onCreate()

        AndroidInjection.inject(this)
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        val currentLocationId = preferences.get().getLong(CURRENT_LOCATION_ID, DEFAULT_LOCATION_ID)

        weatherRepo.get()
                .getCurrentWeather(request = CurrentWeatherRequest(locationId = currentLocationId))
                .subscribe(
                        {
                            Log.i("simpleweather", "data sync update done $it")
                            jobFinished(params, true)
                        },
                        {
                            Log.e("simpleweather", it.localizedMessage, it)
                            jobFinished(params, true)
                        }
                ).also { task = it }

        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        task?.dispose()

        return true
    }

}