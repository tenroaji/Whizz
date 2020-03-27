package id.magau.whizz.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Andi Tenroaji Ahmad on 11/18/2019.
 */

data class ModelResponseRegister (
    @SerializedName("diagnostic")
    var diagnostic : ModelDiagnostic? = null,
    @SerializedName("response")
    var response : ModelLogin? = null
)

data class reqBodyRegister(
    val nama: String?,
    val user: String?,
    val ktp: String?,
    val email: String?,
    val hp: String?,
    val jk: String?,
    val alamat: String?,
    val password: String?
)


data class ModelRegister(
    @SerializedName("nama")
    var nama : String?="",
    @SerializedName("user")
    var user : String?="",
    @SerializedName("ktp")
    var ktp : Int?= 0,
    @SerializedName("email")
    var email : String?= "",
    @SerializedName("hp")
    var hp : Int?= 0,
    @SerializedName("jk")
    var jk : String?= "",
@SerializedName("alamat")
    var alamat : String?= "",
    @SerializedName("password")
    var password: String? = ""
    )