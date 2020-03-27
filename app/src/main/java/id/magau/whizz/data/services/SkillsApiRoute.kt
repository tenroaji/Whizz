package id.magau.whizz.data.services


import id.magau.whizz.data.model.ModelResponseMainMenu
import id.magau.whizz.data.model.ModelResponseSkills
import id.magau.whizz.data.model.ModelResponseSkillsDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by Andi Tenroaji Ahmad on 9/26/2019.
 */

interface SkillsApiRoute {
//    @POST("login")
//     fun loginUser(
//        @Body body : reqBodyLogin?
//    ): Call<ModelResponseLogin>

    @GET("student/product")
    fun allSkill(@Header("authorization") token: String?): Call<ModelResponseSkills>

    @GET("student/product/detail")
    fun detailSkill(
        @Header("authorization") token: String?,
        @Query("id_product") idProduct : String?): Call<ModelResponseSkillsDetail>


}
