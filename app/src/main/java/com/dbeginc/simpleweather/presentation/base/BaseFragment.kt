package com.dbeginc.simpleweather.presentation.base

import android.arch.lifecycle.ViewModelProvider
import android.content.SharedPreferences
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * Created by darel on 26.03.18.
 *
 * Base Fragment containing default
 */
open class BaseFragment : DaggerFragment() {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var preferences: SharedPreferences

}