package com.dbeginc.simpleweather.presentation.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.dbeginc.simpleweather.domain.entities.RequestState
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by darel on 26.03.18.
 *
 * Base ViewModel
 */
abstract class BaseViewModel  : ViewModel() {
    protected abstract val subscriptions: CompositeDisposable
    protected abstract val requestState: MutableLiveData<RequestState>

    /**
     * Request state event
     * This method help me
     * to subscribe to the status
     * of any request made by viewModel
     * And also avoid memory leak :))
     */
    fun getRequestStateEvent() : LiveData<RequestState> = requestState

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }
}