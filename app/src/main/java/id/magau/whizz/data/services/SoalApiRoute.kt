package id.magau.whizz.data.services


import id.magau.whizz.data.model.*
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Andi Tenroaji Ahmad on 9/26/2019.
 */

interface SoalApiRoute{
    companion object{
        private const val PREF_SOAL = "tryout"
    }
    @GET(PREF_SOAL)
    fun getSoal(
        @Header("authorization") token: String?
    ): Call<ModelResponseSoal>

    @GET("tryoutonline")
    fun buySoal(
        @Header("authorization") token: String?,
    @Query("idproduct") idProduct : String?
    ): Call<ModelResponseSoal>


    @GET("student/exam/hasil-sub-section")
    fun getPembahasan(
        @Header("authorization") token: String?,
        @Query("uuidhistory")uuid : String?
    ): Call<ModelResponseSoal>


    @GET("doneTryout?is_online")
    fun sendFinishSoal(
        @Header("authorization") token: String?
    ): Call<ModelResponseSoal>

    @POST("jawabTWK")
    fun sendTWK(
        @Header("authorization") token: String?,
        @Query("id_soal") id_soal : String,
        @Query("pilihan") pilihan : String,
        @Query("id_history") id_history : String
    ): Call<ModelResponseJawabSoal>

    @POST("jawabTIU")
    fun sendTIU(
        @Header("authorization") token: String?,
        @Query("id_soal") id_soal : String,
        @Query("pilihan") pilihan : String,
        @Query("id_history") id_history : String
    ): Call<ModelResponseJawabSoal>

    @POST("student/exam/sub-section-answer")
    fun sendExam(
        @Header("authorization") token: String?,
        @Body data : ModelSendExam
    ): Call<ModelDiagnostic>

    @GET("student/exam/hasil-sub-section")
    fun getHasil(
        @Header("authorization") token: String?,
        @Query("uuid_sub_section")uuid : String?
    ): Call<ModelResponseHasilAnalisis>
}
