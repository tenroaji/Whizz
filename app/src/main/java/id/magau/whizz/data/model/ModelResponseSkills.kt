package id.magau.whizz.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Andi Tenroaji Ahmad on 11/18/2019.
 */

data class ModelResponseSkills (
    @SerializedName("diagnostic")
    var diagnostic : ModelDiagnostic? = null,
    @SerializedName("response")
    var response : ArrayList<ModelProducts?>? = null
)


data class ModelSkills(
    @SerializedName("jenis")
    var jenis : String?="",
    @SerializedName("title")
    var title : String?="",
    @SerializedName("gambar")
    var gambar : Int?= 0,
    @SerializedName("rate")
    var rate : Float?= 0F,
    @SerializedName("harga")
    var harga : String?= ""
)
