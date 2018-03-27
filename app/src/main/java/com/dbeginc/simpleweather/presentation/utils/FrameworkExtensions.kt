package com.dbeginc.simpleweather.presentation.utils

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.databinding.BindingAdapter
import android.net.ConnectivityManager
import android.os.Build
import android.support.transition.Slide
import android.support.v4.app.FragmentActivity
import android.support.v4.view.GravityCompat
import android.text.format.DateUtils
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dbeginc.simpleweather.R
import com.dbeginc.simpleweather.presentation.settings.SettingsFragment
import com.dbeginc.simpleweather.presentation.viewmodels.SettingLocation
import com.dbeginc.simpleweather.presentation.weather.CurrentWeatherFragment


/**
 * Created by darel on 26.03.18.
 *
 * File containing many functions
 * that extend the possibilities of the default android UI Framework
 */

fun scheduleDataSync(context: Context) {
    val scheduler : JobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        if (scheduler.getPendingJob(7125) != null)
            return

    } else {
        if (scheduler.allPendingJobs.find { it.id == 7125 } != null)
            return
    }

    val serviceComponent = ComponentName(context, DataSync::class.java)

    val jobRequirement = JobInfo.Builder(7125, serviceComponent)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .setRequiresCharging(false)
            .setPersisted(true)
            .setPeriodic(30 * DateUtils.MINUTE_IN_MILLIS)
            .build()

    scheduler.schedule(jobRequirement)
}

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val activeNetworkInfo = connectivityManager.activeNetworkInfo

    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}


const val CURRENT_LOCATION_ID = "current_location_id"
const val DEFAULT_LOCATION_ID : Long = 709930 /*DNIPRO*/

val defaultLocations: List<SettingLocation> = listOf(
        SettingLocation(id = 703448, location = "Kiev"),
        SettingLocation(id = 706483, location = "Kharkiv"),
        SettingLocation(id = 709930, location = "Dnipropetrovsk"),
        SettingLocation(id = 687700, location = "Zaporozhye")
)

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

fun goToSettingsScreen(container: FragmentActivity) {
    val settingsFragment = SettingsFragment()

    settingsFragment.apply {
        enterTransition = Slide(GravityCompat.END)
    }

    container.supportFragmentManager
            .findFragmentById(R.id.mainContent)
            ?.apply {
                exitTransition = Slide(GravityCompat.START)
            }


    val transaction = container.supportFragmentManager.beginTransaction()

    transaction.replace(
            R.id.mainContent,
            settingsFragment,
            CurrentWeatherFragment::class.java.simpleName
    )

    transaction.commit()
}

fun goToWeatherScreen(container: FragmentActivity, isForward: Boolean) {
    val currentWeatherFragment = CurrentWeatherFragment()

    if (!isForward) {
        currentWeatherFragment.apply {
            enterTransition = Slide(GravityCompat.START)
        }

        container.supportFragmentManager
                .findFragmentById(R.id.mainContent)
                ?.apply {
                    exitTransition = Slide(GravityCompat.END)
                }
    }

    val transaction = container.supportFragmentManager.beginTransaction()

    transaction.replace(R.id.mainContent, currentWeatherFragment, CurrentWeatherFragment::class.java.simpleName)

    transaction.commit()
}

/**
 * Handle back key
 * @return true if it's not last screen false if last screen
 */
fun handleBackNavigation(container: FragmentActivity) : Boolean {
    return when(container.supportFragmentManager.findFragmentById(R.id.mainContent)) {
        is CurrentWeatherFragment -> false
        is SettingsFragment -> true
        else -> false
    }
}