package id.magau.whizz.data.services


import id.magau.whizz.data.model.*
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

    @GET("student/product/mycourse")
    fun mySkill(@Header("authorization") token: String?): Call<ModelResponseSkills>

    @GET("student/product/detail")
    fun detailSkill(
        @Header("authorization") token: String?,
        @Query("uuid_course") idProduct : String?): Call<ModelResponseSkillsDetail>


    @GET("student/product/faq")
    fun dataFAQ(
        @Header("authorization") token: String?,
        @Query("uuid_produk") idProduct : String?): Call<ModelResponseFAQ>

    @GET("student/product/section")
    fun dataKurikulum(
        @Header("authorization") token: String?,
        @Query("uuidcourse") idProduct : String?): Call<ModelResponseKurikulum>

    @GET("student/comment")
    fun dataComment(
        @Header("authorization") token: String?,
        @Header("id") idProduct: String?
    ): Call<ModelResponseComments>

}
