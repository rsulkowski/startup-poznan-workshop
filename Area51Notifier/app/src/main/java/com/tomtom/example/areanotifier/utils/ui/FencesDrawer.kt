/**
 * Copyright (c) 2015-2019 TomTom N.V. All rights reserved.
 *
 * This software is the proprietary copyright of TomTom N.V. and its subsidiaries and may be used
 * for internal evaluation purposes or commercial use strictly subject to separate licensee
 * agreement between you and TomTom. If you are the licensee, you are only permitted to use
 * this Software in accordance with the terms of your license agreement. If you are not the
 * licensee then you are not authorised to use this software in any manner and should
 * immediately return it to TomTom N.V.
 */
package com.tomtom.example.areanotifier.utils.ui

import android.graphics.Color
import com.tomtom.example.areanotifier.service.FencesServiceResponse
import com.tomtom.online.sdk.map.PolygonBuilder
import com.tomtom.online.sdk.map.TomtomMap

class FencesDrawer(private val tomtomMap: TomtomMap) {

    fun drawPolygonFences(fences: List<FencesServiceResponse>) {
        tomtomMap.overlaySettings.removeOverlays()
        fences.forEach {
            addFence(it)
        }
    }

    private fun addFence(fence: FencesServiceResponse) {
        // TODO: Add fence polygon
    }

    companion object {
        private const val RECTANGLE_COLOR = Color.RED

        //Fence color opacity
        private const val FENCE_OPACITY = 0.5f
    }

}