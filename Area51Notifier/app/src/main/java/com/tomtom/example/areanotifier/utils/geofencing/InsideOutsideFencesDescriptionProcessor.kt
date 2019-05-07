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
package com.tomtom.online.sdk.samples.ktx.cases.geofencing.report.utils

import android.content.Context
import com.tomtom.example.areanotifier.R
import com.tomtom.online.sdk.geofencing.data.report.Report

class InsideOutsideFencesDescriptionProcessor : FencesDescriptionProcessor {

    override fun isValid(report: Report): Boolean {
        return report.outside.isNotEmpty().and(report.inside.isNotEmpty())
    }

    override fun getText(context: Context, report: Report): String {
        return context.resources.getString(
            R.string.report_service_both_types_of_fences,
            GeofencingReportParser.fenceDetailsToString(report.inside),
            GeofencingReportParser.fenceDetailsToString(report.outside))
    }

}
