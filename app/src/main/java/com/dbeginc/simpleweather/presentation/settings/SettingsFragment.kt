package com.dbeginc.simpleweather.presentation.settings


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dbeginc.simpleweather.R
import com.dbeginc.simpleweather.databinding.FragmentSettingsBinding
import com.dbeginc.simpleweather.databinding.SettingLocationLayoutBinding
import com.dbeginc.simpleweather.presentation.base.BaseFragment
import com.dbeginc.simpleweather.presentation.utils.CURRENT_LOCATION_ID
import com.dbeginc.simpleweather.presentation.utils.DEFAULT_LOCATION_ID
import com.dbeginc.simpleweather.presentation.utils.defaultLocations
import com.dbeginc.simpleweather.presentation.utils.goToWeatherScreen
import com.dbeginc.simpleweather.presentation.viewmodels.SettingLocation


/**
 * A simple Settings Fragment [BaseFragment] subclass.
 *
 * We could use PreferenceFragment but prefer this,
 * give me more control
 */
class SettingsFragment : BaseFragment() {
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_settings,
                container,
                false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.setSupportActionBar(binding.settingsToolbar)

        binding.settingsToolbar.setNavigationOnClickListener {
            activity?.let {
                goToWeatherScreen(container = it, isForward = false)
            }
        }

        val currentLocation = preferences.getLong(CURRENT_LOCATION_ID, DEFAULT_LOCATION_ID)
        // Hide current location from list of choice
        // We could have put this adapter as member of the fragment
        // So we dont have to recreate it on every OnViewCreated
        // But since it's a static list of only 4 items. it's not that necessary
        val simpleAdapter = SimpleAdapter(
                data = defaultLocations.filterNot { it.id == currentLocation },
                onClick =this::updateLocationAndExit
        )

        binding.cities.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding.cities.adapter = simpleAdapter

        binding.cities.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

    }

    private fun updateLocationAndExit(location: SettingLocation) {
        preferences.edit().putLong(CURRENT_LOCATION_ID, location.id).apply()

        activity?.let {
            goToWeatherScreen(container = it, isForward = false)
        }
    }

    inner class SimpleAdapter(private val data: List<SettingLocation>, private val onClick: (SettingLocation) -> Unit) : RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
            return SimpleViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.context),
                            R.layout.setting_location_layout,
                            parent,
                            false
                    ),
                    onClick = onClick
            )
        }

        override fun getItemCount(): Int = data.size

        override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) =
                holder.bindLocation(data[position])


        inner class SimpleViewHolder(private val binding: SettingLocationLayoutBinding, onClick: (SettingLocation) -> Unit) : RecyclerView.ViewHolder(binding.root) {
            init {
                binding.root.setOnClickListener {
                    onClick(binding.location!!)
                }
            }

            fun bindLocation(location: SettingLocation) {
                binding.location = location

                binding.executePendingBindings()
            }
        }
    }

}
