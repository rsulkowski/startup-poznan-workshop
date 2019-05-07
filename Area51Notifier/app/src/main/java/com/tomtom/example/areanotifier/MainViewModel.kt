package com.tomtom.example.areanotifier

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.tomtom.example.areanotifier.utils.arch.SingleLiveEvent


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var mapAction: SingleLiveEvent<MapAction> =
        SingleLiveEvent()

    fun applyOnMap(action: MapAction) {
        mapAction.value = action
    }

    fun mapAction(): LiveData<MapAction> {
        return mapAction
    }

}
