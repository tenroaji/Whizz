package id.magau.whizz.data.services


import id.magau.whizz.data.model.ModelResponseRiwayat
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Created by Andi Tenroaji Ahmad on 9/26/2019.
 */

interface EventApiRoute{
    @GET("getHistory")
    fun getEvents(
        @Header("authorization") token: String?,
        @Header("idproduct") id: String?,
        @Query("page") page: String?
    ): Call<ModelResponseRiwayat>

}
