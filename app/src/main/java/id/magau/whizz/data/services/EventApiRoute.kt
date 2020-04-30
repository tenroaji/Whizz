package id.magau.whizz.data.services


import id.magau.whizz.data.model.ModelResponseEvent
import id.magau.whizz.data.model.ModelResponseEventDetail
import id.magau.whizz.data.model.ModelResponseRiwayat
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Created by Andi Tenroaji Ahmad on 9/26/2019.
 */

interface EventApiRoute {
    @GET("student/event")
    fun getEvents(
        @Header("authorization") token: String?,
        @Query("page") page: String?
    ): Call<ModelResponseEvent>


    @GET("student/detail-event")
    fun getEventDetail(
        @Header("authorization") token: String?,
        @Query("uuid_event") id: String?
    ): Call<ModelResponseEventDetail>

    @GET("student/search/event")
    fun searchEvent(
        @Header("authorization") token: String?,
        @Query("event") event: String?
    ): Call<ModelResponseEvent>

    @GET("student/myevent")
    fun myEvent(
        @Header("authorization") token: String?,
        @Query("page") page: String?
    ): Call<ModelResponseEvent>
}
