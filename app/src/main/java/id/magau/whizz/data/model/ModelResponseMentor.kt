package id.magau.whizz.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Andi Tenroaji Ahmad on 11/18/2019.
 */

data class ModelResponseMentor (
    @SerializedName("diagnostic")
    var diagnostic : ModelDiagnostic? = null,
    @SerializedName("response")
    var response : ModelMentor? = null
)


data class ModelMentor(
    @SerializedName("nama")
    var nama : String?="",
    @SerializedName("pekerjaan")
    var pekerjaan : String?="",
    @SerializedName("gambar")
    var gambar : Int?= 0,
    @SerializedName("desc")
    var desc : String?= ""
)
