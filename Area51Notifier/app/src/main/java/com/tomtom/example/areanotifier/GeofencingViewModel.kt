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

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.tomtom.example.areanotifier.utils.arch.ResourceLiveData
import com.tomtom.example.areanotifier.utils.geofencing.GeofencingReportWithFenceDetails
import com.tomtom.online.sdk.common.location.LatLng
import com.tomtom.online.sdk.geofencing.data.report.ReportServiceQueryBuilder
import com.tomtom.example.areanotifier.utils.geofencing.GeofencingRequester
import java.util.*

class GeofencingViewModel(application: Application) : AndroidViewModel(application) {

    var response = ResourceLiveData<GeofencingReportWithFenceDetails>()

    fun requestReportWithFences(location: LatLng) {
        val queryBuilder = prepareQueryBuilder(location)
        GeofencingRequester(getApplication()).obtainReportWithFences(queryBuilder, response)
    }

    private fun prepareQueryBuilder(position: LatLng): ReportServiceQueryBuilder {
        return ReportServiceQueryBuilder.create(position.toLocation())
            .withProject(UUID.fromString(GEO_FENCE_PROJECT_ID))
            .withRange(QUERY_RANGE_IN_METERS)
    }

    companion object {
        private const val QUERY_RANGE_IN_METERS = 100_000f
        private const val GEO_FENCE_PROJECT_ID = "1a7af71a-1607-48a2-a6c1-ec04a4909513"
    }

}
