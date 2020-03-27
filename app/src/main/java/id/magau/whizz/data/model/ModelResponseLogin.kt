package id.magau.whizz.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Andi Tenroaji Ahmad on 11/18/2019.
 */

data class ModelResponseLogin (
    @SerializedName("diagnostic")
    var diagnostic : ModelDiagnostic? = null,
    @SerializedName("response")
    var response : ModelLogin? = null
)

data class reqBodyLogin(
    var username:String?="",
    var password:String?=""
)

data class ModelLogin(
    @SerializedName("token_type")
    var token_type : String?="",
    @SerializedName("expires_in")
    var expires_in : Int?= 0,
    @SerializedName("access_token")
    var access_token : String?= "",
    @SerializedName("refresh_token")
    var refresh_token : String?= "",
    @SerializedName("user")
    var user : User?= null
)

data class User(
    @SerializedName("id")
    var id : Int?=0,
    @SerializedName("name")
    var name : String?= "",
    @SerializedName("email")
    var email : String?= "",
    @SerializedName("role")
    var role : String?= ""
)