package id.magau.whizz.data.services


import id.magau.whizz.data.model.ModelResponseMainMenu
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Created by Andi Tenroaji Ahmad on 9/26/2019.
 */

interface MainMenuApiRoute{
//    @POST("login")
//     fun loginUser(
//        @Body body : reqBodyLogin?
//    ): Call<ModelResponseLogin>

    @GET("student")
    fun mainMenu(@Header("authorization") token: String?): Call<ModelResponseMainMenu>


}
