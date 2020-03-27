package id.magau.whizz.data.services


import id.magau.whizz.data.model.ModelResponseLogin
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

interface LoginApiRoute{
//    @POST("login")
//     fun loginUser(
//        @Body body : reqBodyLogin?
//    ): Call<ModelResponseLogin>

    @POST("login")
    fun loginUser(
        @Header("username") username: String?,
        @Header("password") password: String?
    ): Call<ModelResponseLogin>


}
