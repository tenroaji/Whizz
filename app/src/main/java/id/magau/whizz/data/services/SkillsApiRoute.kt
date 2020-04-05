package id.magau.whizz.data.services


import id.magau.whizz.data.model.*
import retrofit2.Call
import retrofit2.http.*

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
    fun mySkill(@Header("authorization") token: String?): Call<ModelResponseMySkills>

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

    @FormUrlEncoded
    @POST("student/comment/insert")
    fun sendComment(
        @Header("authorization") token: String?,
        @Field("idcourse") idProduct: String?,
        @Field("comment") comment: String?
    ): Call<ModelResponseDiagnostic>

    @FormUrlEncoded
    @POST("student/rating")
    fun sendRating(
        @Header("authorization") authorization: String?,
        @Field("idcourse")  idcourse : String,
        @Field("rating")  rating : Int
    ): Call<ModelResponseDiagnostic>

    @FormUrlEncoded
    @POST("student/reply/insert")
    fun sendReplys(
        @Header("authorization") authorization: String?,
        @Field("idcomment")  idcourse : String,
        @Field("reply")  reply : String
    ): Call<ModelResponseDiagnostic>

    @GET("student/reply")
    fun getReplys(
        @Header("authorization") authorization: String?,
        @Header("id")  idComment : String
    ): Call<ModelResponseReplys>



}
