package com.dbeginc.simpleweather.presentation.weather


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dbeginc.simpleweather.R


/**
 * A simple [Fragment] subclass.
 */
class CurrentWeatherFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_current_weather, container, false)
    }

}// Required empty public constructor
