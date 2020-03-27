package id.magau.whizz.data.services

import id.magau.whizz.data.model.ModelResponseLogin
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST


interface LoginServices {

    @POST("login")
    suspend fun loginUser(
        @Header("username") username: String?,
        @Header("password") password: String?
    ): Response<ModelResponseLogin>
}