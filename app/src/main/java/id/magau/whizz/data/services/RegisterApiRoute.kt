package id.magau.whizz.data.services


import id.magau.whizz.data.model.ModelResponseRegister
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Created by Andi Tenroaji Ahmad on 9/26/2019.
 */

interface RegisterApiRoute{
    companion object{
        private const val PREF_REGISTER = "register"
    }
    @POST(PREF_REGISTER)
    fun register(
        @Header("nama") nama: String?,
        @Header("user") user: String?,
        @Header("ktp") ktp: String?,
        @Header("email") email: String?,
        @Header("hp") hp: String?,
        @Header("jk") jk: String?,
        @Header("alamat") alamat: String?,
        @Header("password") password: String?
    ): Call<ModelResponseRegister>


}
