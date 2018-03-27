package com.dbeginc.simpleweather.presentation.weather


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.*
import com.dbeginc.simpleweather.R
import com.dbeginc.simpleweather.databinding.FragmentCurrentWeatherBinding
import com.dbeginc.simpleweather.domain.entities.RequestState
import com.dbeginc.simpleweather.presentation.base.BaseFragment
import com.dbeginc.simpleweather.presentation.utils.*
import com.dbeginc.simpleweather.presentation.viewmodels.CurrentWeatherModel
import com.dbeginc.simpleweather.presentation.viewmodels.WeatherViewModel


/**
 * A simple [Fragment] subclass.
 */
class CurrentWeatherFragment : BaseFragment() {
    private lateinit var viewModel: WeatherViewModel
    private lateinit var binding: FragmentCurrentWeatherBinding
    private val stateObserver = Observer<RequestState> { onStateChanged(state = it!!) }
    private val weatherObserver = Observer<CurrentWeatherModel> {
        binding.current = it

        binding.executePendingBindings()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[WeatherViewModel::class.java]
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getRequestStateEvent().observe(this, stateObserver)

        viewModel.getCurrentWeather().observe(this, weatherObserver)

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater?.inflate(R.menu.weather_feature_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.action_refresh -> retrieveCurrentWeather()
            R.id.action_open_settings -> activity?.let {
                goToSettingsScreen(container = it)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_current_weather,
                container,
                false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.setSupportActionBar(binding.weatherToolbar)

        retrieveCurrentWeather()

    }

    private fun onStateChanged(state: RequestState) {
        when (state) {
            RequestState.LOADING -> binding.loadingIndicator.show()
        // response can be really fast so if we hide the progressBar right away
        // it's will create a bad UX, so we hide it with delay
            RequestState.COMPLETED -> binding.root.postDelayed(binding.loadingIndicator::hide, 500)
            RequestState.FAILED -> binding.root.postDelayed(this::onRequestFailed, 500)
        }
    }

    private fun onRequestFailed() {
        binding.loadingIndicator.hide()

        Snackbar.make(binding.weatherLayout, R.string.error_current_weather, Snackbar.LENGTH_LONG)
                .setAction(R.string.retry) { retrieveCurrentWeather() }
                .setActionTextColor(Color.RED)
                .show()
    }

    private fun retrieveCurrentWeather() = if (context?.isNetworkAvailable() == true) {
        /* Better put default value it in constant but for now it's fine here since it's only demo */
        val currentLocation = preferences.getLong(CURRENT_LOCATION_ID, DEFAULT_LOCATION_ID)

        viewModel.loadCurrentWeather(locationId = currentLocation)

    } else {
        Snackbar.make(binding.weatherLayout, R.string.network_unavailable, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.close) { /*Will automatically dismiss the snackbar*/ }
                .show()
    }
}
