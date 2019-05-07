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

import android.content.Context
import com.google.common.collect.ImmutableList
import com.tomtom.online.sdk.common.location.LatLng
import com.tomtom.online.sdk.geofencing.data.report.FenceDetails
import com.tomtom.online.sdk.geofencing.data.report.Report
import com.tomtom.online.sdk.map.*
import com.tomtom.online.sdk.samples.ktx.cases.geofencing.report.utils.InsideFencesDescriptionProcessor
import com.tomtom.online.sdk.samples.ktx.cases.geofencing.report.utils.InsideOutsideFencesDescriptionProcessor
import com.tomtom.online.sdk.samples.ktx.cases.geofencing.report.utils.OutsideFencesDescriptionProcessor

import com.tomtom.example.areanotifier.R

class FenceMarkersDrawer(private val context: Context, private val tomtomMap: TomtomMap) {

    fun drawMarkersForFences(report: Report) {
        val fenceDetailsList = ArrayList<FenceDetails>()
        fenceDetailsList.addAll(report.inside)
        fenceDetailsList.addAll(report.outside)

        fenceDetailsList.forEach { fenceDetail ->
            // TODO: Add markers for fences with FENCE_MARKERS_TAG
        }
    }

    fun drawDraggableMarkerAtDefaultPosition() {
        // TODO: Add draggable marker for with mocked location with DRAGGABLE_MARKER_TAG
    }

    private fun findDraggableMarker(): Marker? {
        return tomtomMap.markerSettings.findMarkerByTag(DRAGGABLE_MARKER_TAG).orNull()
    }

    fun updateDraggableMarkerText(report: Report) {
        val descriptionProcessors = ImmutableList.of(
            InsideFencesDescriptionProcessor(),
            OutsideFencesDescriptionProcessor(),
            InsideOutsideFencesDescriptionProcessor()
        )

        findDraggableMarker()?.let { marker ->
            val baseMarkerBalloon = marker.markerBalloon.get() as BaseMarkerBalloon
            for (descriptionProcessor in descriptionProcessors) {
                if (descriptionProcessor.isValid(report)) {
                    baseMarkerBalloon.setText(descriptionProcessor.getText(context, report))
                }
            }
        }
    }

    fun removeFenceMarkers() {
        tomtomMap.markerSettings.removeMarkerByTag(FENCE_MARKERS_TAG)
    }

    private fun createMarkerIcon(): Icon {
        return Icon.Factory.fromResources(context, R.drawable.ic_marker_entry_point)
    }

    companion object {
        private const val DRAGGABLE_MARKER_TAG = "DRAGGABLE_MARKER"
        const val FENCE_MARKERS_TAG = "FENCE_MARKERS"
    }
}