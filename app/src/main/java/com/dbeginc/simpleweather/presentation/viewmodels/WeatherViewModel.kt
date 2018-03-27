package com.dbeginc.simpleweather.presentation.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.dbeginc.simpleweather.domain.ThreadProvider
import com.dbeginc.simpleweather.domain.entities.CurrentWeather
import com.dbeginc.simpleweather.domain.entities.CurrentWeatherRequest
import com.dbeginc.simpleweather.domain.entities.RequestState
import com.dbeginc.simpleweather.domain.repository.WeatherRepository
import com.dbeginc.simpleweather.presentation.base.BaseViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import kotlin.math.roundToInt

/**
 * Created by darel on 26.03.18.
 *
 * Weather Viewmodel
 */
class WeatherViewModel @Inject constructor(private val weatherRepo: WeatherRepository, private val threads: ThreadProvider) : BaseViewModel() {
    override val subscriptions: CompositeDisposable = CompositeDisposable()
    override val requestState: MutableLiveData<RequestState> = MutableLiveData()
    private val _currentWeather: MutableLiveData<CurrentWeatherModel> = MutableLiveData()

    fun getCurrentWeather() : LiveData<CurrentWeatherModel> = _currentWeather

    fun loadCurrentWeather(locationId: Long, language: String = "ru", units: String = "metric") {
        weatherRepo.getCurrentWeather(request = CurrentWeatherRequest(locationId = locationId, language = language, units = units))
                .doOnSubscribe { requestState.postValue(RequestState.LOADING) }
                .doAfterNext { requestState.postValue(RequestState.COMPLETED) }
                .doOnError { requestState.postValue(RequestState.FAILED) }
                .map { weather -> weather.toUI() }
                .observeOn(threads.UI)
                .subscribe(_currentWeather::postValue, { Log.e("simpleweather", it.localizedMessage, it) }) /*Enough for demo app */
    }

    /**
     * Mapper of domain object to UI object
     * this should be in mapper utility class
     * but since we only have one pojo for
     * now it's can be here
     */
    private fun CurrentWeather.toUI(): CurrentWeatherModel {
        return CurrentWeatherModel(
                location = location,
                icon = icon,
                summary = summary,
                temperature = temperature.roundToInt().toString(),
                temperatureMin = minTemperature.toString(),
                temperatureMax = maxTemperature.toString()
        )
    }

}