package id.magau.whizz.data.services


import id.magau.whizz.data.model.ModelResponseLogin
import id.magau.whizz.data.model.ModelResponsePembayaran
import id.magau.whizz.data.model.reqBodyLogin
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Created by Andi Tenroaji Ahmad on 9/26/2019.
 */

interface PembayaranApiRoute{
    @GET("student/bank")
    fun bank(
        @Header("authorization") authorization: String?
    ): Call<ModelResponsePembayaran>


}
