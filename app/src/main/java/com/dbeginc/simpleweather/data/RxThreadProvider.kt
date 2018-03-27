package com.dbeginc.simpleweather.data

import com.dbeginc.simpleweather.domain.ThreadProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by darel on 26.03.18.
 *
 * Reactive stream thread provider
 */
object RxThreadProvider : ThreadProvider {
    override val UI: Scheduler = AndroidSchedulers.mainThread()
    override val CP: Scheduler = Schedulers.computation()
    override val IO: Scheduler = Schedulers.io()
}