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
package com.tomtom.example.areanotifier.utils.geofencing

import android.content.Context
import com.tomtom.example.areanotifier.service.FencesServiceResponse
import com.tomtom.example.areanotifier.service.obtainFence
import com.tomtom.example.areanotifier.utils.arch.Resource
import com.tomtom.example.areanotifier.utils.arch.ResourceLiveData
import com.tomtom.online.sdk.common.rx.RxContext
import com.tomtom.online.sdk.geofencing.GeofencingApi
import com.tomtom.online.sdk.geofencing.data.report.ReportServiceQueryBuilder
import com.tomtom.online.sdk.geofencing.data.report.ReportServiceResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.SerialDisposable
import io.reactivex.schedulers.Schedulers

class GeofencingRequester(private val context: Context) : RxContext {

    private val disposable = SerialDisposable()
    private val geofencingApi = GeofencingApi.create(context)

    fun obtainReportWithFences(
        routeServiceQueryBuilder: ReportServiceQueryBuilder,
        result: ResourceLiveData<GeofencingReportWithFenceDetails>
    ) {

        result.value = Resource.loading(null)
        val reportQuery = routeServiceQueryBuilder.build()

        disposable.set(
            geofencingApi.obtainReport(reportQuery).toObservable()
                .map { reportServiceResponse ->
                    getFencesServiceResponseObservable(reportServiceResponse)
                }
                .subscribeOn(workingScheduler)
                .observeOn(resultScheduler)
                .subscribe(
                    { response ->
                        result.value = Resource.success(response)
                    }, { error -> result.value = Resource.error(null, Error(error.message)) })
        )
    }

    private fun getFencesServiceResponseObservable(reportServiceResponse: ReportServiceResponse): GeofencingReportWithFenceDetails {
        return Observable.fromIterable(reportServiceResponse.report.outside + reportServiceResponse.report.inside)
            .flatMap { fenceDetails -> obtainFence(context, fenceDetails.fence.id) }
            .toList()
            .map {
                GeofencingReportWithFenceDetails(reportServiceResponse, it)
            }
            .blockingGet()
    }

    override fun getWorkingScheduler() = Schedulers.newThread()

    override fun getResultScheduler() = AndroidSchedulers.mainThread()!!

}

class GeofencingReportWithFenceDetails(
    val reportServiceResponse: ReportServiceResponse,
    val fencesServiceResponse: List<FencesServiceResponse>
)