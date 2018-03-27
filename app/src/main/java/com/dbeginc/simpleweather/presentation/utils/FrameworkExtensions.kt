package com.dbeginc.simpleweather.presentation.utils

import android.databinding.BindingAdapter
import android.support.transition.Slide
import android.support.v4.app.FragmentActivity
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dbeginc.simpleweather.R
import com.dbeginc.simpleweather.presentation.settings.SettingsFragment
import com.dbeginc.simpleweather.presentation.weather.CurrentWeatherFragment

/**
 * Created by darel on 26.03.18.
 *
 * File containing many functions
 * that extend the possibilities of the default android UI Framework
 */

const val CURRENT_LOCATION_ID = "current_location_id"

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.show() {
    visibility = View.VISIBLE
}

@BindingAdapter("setIcon")
fun setIcon(imageView: ImageView, url: String?) {
    if (url != null && url.isNotEmpty()) {
        Glide.with(imageView)
                .load("http://openweathermap.org/img/w/$url.png")
                .apply(RequestOptions.skipMemoryCacheOf(true))
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.RESOURCE))
                .apply(RequestOptions.centerCropTransform())
                .into(imageView)
    }
}

fun goToSettingsScreen(container: FragmentActivity, isForward: Boolean) {
    val currentWeatherFragment = SettingsFragment()

    val transaction = container.supportFragmentManager.beginTransaction()

    transaction.replace(
            R.id.mainContent,
            currentWeatherFragment,
            CurrentWeatherFragment::class.java.simpleName
    )

    transaction.commit()
}

fun goToWeatherScreen(container: FragmentActivity, isForward: Boolean) {
    val currentWeatherFragment = CurrentWeatherFragment()

    /*if (isForward) {
        currentWeatherFragment.apply {
            enterTransition = Slide(GravityCompat.START)
            exitTransition = Slide(GravityCompat.START)
        }
    } else {
        currentWeatherFragment.apply {
            enterTransition = Slide(GravityCompat.START)
            exitTransition = Slide(GravityCompat.END)
        }

        container.supportFragmentManager.findFragmentById(R.id.mainContent).apply {
            enterTransition = Slide(GravityCompat.END)
            exitTransition = Slide(GravityCompat.END)
        }
    }
*/
    val transaction = container.supportFragmentManager.beginTransaction()

    transaction.replace(R.id.mainContent, currentWeatherFragment, CurrentWeatherFragment::class.java.simpleName)

    transaction.commit()
}
