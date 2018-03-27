package com.dbeginc.simpleweather.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dbeginc.simpleweather.R
import com.dbeginc.simpleweather.presentation.utils.goToWeatherScreen
import com.dbeginc.simpleweather.presentation.utils.handleBackNavigation
import com.dbeginc.simpleweather.presentation.utils.scheduleDataSync

class MainActivity : AppCompatActivity() {

    override fun onBackPressed() {
        if (handleBackNavigation(container = this)) goToWeatherScreen(container = this, isForward = false)
        else super.onBackPressed()
    }

    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)
        setContentView(R.layout.activity_main)

        if (savedState == null) {
            goToWeatherScreen(container = this, isForward = true)

            scheduleDataSync(context = applicationContext)
        }
    }
}
