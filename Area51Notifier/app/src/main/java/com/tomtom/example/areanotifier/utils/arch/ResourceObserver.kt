package com.tomtom.example.areanotifier.utils.arch

import androidx.lifecycle.Observer
import timber.log.Timber

class ResourceObserver<T>(private val hideLoading: () -> Unit,
                          private val showLoading: () -> Unit,
                          private val onSuccess: (data: T) -> Unit,
                          private val onError: (message: String?) -> Unit) : Observer<Resource<T>> {

    override fun onChanged(resource: Resource<T>?) {
        when (resource?.status) {
            Resource.Status.SUCCESS -> {
                hideLoading()
                if (resource.data != null) {
                    Timber.d("observer -> SUCCESS, ${resource.data} items")
                    onSuccess(resource.data)
                }
            }
            Resource.Status.ERROR -> {
                hideLoading()
                if (resource.error != null) {
                    Timber.d( "observer -> ERROR, ${resource.error}")
                    onError(resource.error.message)
                }
            }
            Resource.Status.LOADING -> {
                showLoading()
                Timber.d("observer -> LOADING")
            }
        }
    }
}