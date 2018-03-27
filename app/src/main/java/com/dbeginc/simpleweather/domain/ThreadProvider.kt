package com.dbeginc.simpleweather.domain

import io.reactivex.Scheduler

/**
 * Created by darel on 26.03.18.
 *
 * Thread Provider Interface
 * Every thread provider must implement interface
 */
interface ThreadProvider {
    /**
     * User interface thread
     */
    val UI: Scheduler

    /**
     * Computation thread
     */
    val CP: Scheduler

    /**
     * Input/output thread
     */
    val IO: Scheduler
}