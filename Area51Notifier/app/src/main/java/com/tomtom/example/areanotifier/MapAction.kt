package com.tomtom.example.areanotifier

import com.tomtom.online.sdk.map.TomtomMap

class MapAction(private val action: TomtomMap.() -> Unit) {

    fun invoke(tomtomMap: TomtomMap) {
        action(tomtomMap)
    }

}