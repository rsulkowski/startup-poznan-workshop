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
package com.tomtom.example.areanotifier

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.tomtom.example.areanotifier.service.FencesServiceResponse
import com.tomtom.example.areanotifier.utils.arch.ResourceObserver
import com.tomtom.example.areanotifier.utils.geofencing.GeofencingReportWithFenceDetails
import com.tomtom.example.areanotifier.utils.ui.FenceMarkersDrawer
import com.tomtom.example.areanotifier.utils.ui.FencesDrawer
import com.tomtom.online.sdk.common.location.LatLng
import com.tomtom.online.sdk.geofencing.data.report.Report
import com.tomtom.online.sdk.map.MapConstants
import com.tomtom.online.sdk.map.Marker
import com.tomtom.online.sdk.map.TomtomMapCallback

class GeofencingFragment : MainFragment() {

    private lateinit var viewModel: GeofencingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_default, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        confViewModel()
        // TODO: - Center on default location
        // TODO: - request the report with fences for default location
        // TODO: - draw default Marker
    }

    override fun onResume() {
        super.onResume()
        confMapActions()
    }

    override fun onPause() {
        super.onPause()
        removeMapActions()
        clearMap()
    }

    private fun confViewModel() {
        // TODO: Configure view model (initialize GeofencingViewModel) and observe on response from report and fences services.
        // TODO: Tip: Use ResourceObserver class for better readability
    }

    private fun processReportResponse(response: GeofencingReportWithFenceDetails) {
        // TODO: Process report service response and the fences service response
    }

    private fun processReport(report: Report) {
        // TODO: Remove old fences markers and draw new one
        // TODO: update text for draggable marker (mocked position marker) with data from report.
    }

    private fun processFenceResponse(fences: List<FencesServiceResponse>) {
        // TODO: Proceed all fences responses to draw fences on map
    }

    private fun drawDefaultMarker() {
        // TODO: Draw default marker
    }

    private fun clearMap() {
        // TODO: Remove overlays and markers from map
    }

    // TODO: OnMarkerDragListener should deselect marker once dragging is started and make a request
    // TODO: to the report service to obtain report with fences ids.
    private val markerDragListener = object : TomtomMapCallback.OnMarkerDragListener {
        override fun onStartDragging(marker: Marker) {
//            marker.deselect()
        }

        override fun onStopDragging(marker: Marker) {
            //TODO: Request report with fences for specified position
//            viewModel.requestReportWithFences(marker.position)
        }

        override fun onDragging(marker: Marker) {
        }
    }

    private fun confMapActions() {
        // TODO: Setup OnMarkerDragListener
    }

    private fun removeMapActions() {
        // TODO: Remove OnMarkerDragListener
    }

    private fun centerOnDefaultLocation() {
        // TODO: Center on default location
    }

    private fun requestReportWithFencesForDefaultLocation() {
        // TODO: Remove overlays which exists on map
        // TODO: Request for new report with fences for the default location
    }

    companion object {
        private const val ZOOM_LEVEL_FOR_EXAMPLE = 16.0
        private val DEFAULT_LOCATION = LatLng(52.4030723, 16.9091882)
    }
}
