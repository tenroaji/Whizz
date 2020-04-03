package id.magau.whizz.data.services


import id.magau.whizz.data.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by Andi Tenroaji Ahmad on 9/26/2019.
 */

interface PembayaranApiRoute {
    @GET("student/bank")
    fun bank(
        @Header("authorization") authorization: String?
    ): Call<ModelResponsePembayaran>


    @FormUrlEncoded
    @POST("student/checkout")
//    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun createCheckout(
        @Header("authorization") authorization: String?,
        @Field("idcourse")  idcourse : String,
        @Field("bank")  bank : String
    ): Call<ModelResponsePembayaranDetail>

}
