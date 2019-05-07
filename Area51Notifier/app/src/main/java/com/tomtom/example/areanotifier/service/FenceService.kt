package com.tomtom.example.areanotifier.service

import android.content.Context
import com.tomtom.online.sdk.common.config.provider.ConfigProvider
import com.tomtom.online.sdk.common.config.provider.PriorityConfigProvider
import com.tomtom.online.sdk.common.location.LatLng
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface FenceService {

    @GET("geofencing/1/fences/{fenceId}")
    fun requestFence(
        @Path("fenceId") fenceId: UUID,
        @Query("key") apiKey: String,
        @Query("showGeoJson") showGeoJson: Boolean = true
    ): Observable<FencesServiceResponse>

}

//TODO: Create retrofit once
fun obtainFence(
    context: Context,
    fenceId: UUID,
    showGeoJson: Boolean = true
): Observable<FencesServiceResponse> {
    val configProvider = PriorityConfigProvider.createProviderManifestFirst(context)
    val config = configProvider.provideConfiguration()[ConfigProvider.GEOFENCING_API_KEY]!!
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.tomtom.com/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val fenceService = retrofit.create(FenceService::class.java)
    return fenceService.requestFence(fenceId, config.value, showGeoJson)
}

class FencesServiceResponse(
    val name: String,
    val geometry: Geometry
)

class Geometry(val coordinates: Polygon)

class Polygon : ArrayList<LineString>() {
    fun getExteriorRing() = get(0)
}

class LineString : ArrayList<Coordinate>() {
    fun getCoordinates(): List<LatLng> = map { it.getLatLng() }.toMutableList()
}

class Coordinate : ArrayList<Double>() {
    private fun getLat() = get(1)
    private fun getLon() = get(0)
    fun getLatLng() = LatLng(getLat(), getLon())
}