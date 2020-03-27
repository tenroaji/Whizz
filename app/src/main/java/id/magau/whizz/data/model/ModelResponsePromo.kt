package id.magau.whizz.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Andi Tenroaji Ahmad on 11/18/2019.
 */

data class ModelResponsePromo (
    @SerializedName("diagnostic")
    var diagnostic : ModelDiagnostic? = null,
    @SerializedName("response")
    var response : ModelPromo? = null
)


data class ModelPromo(
    @SerializedName("jenis")
    var jenis : String?="",
    @SerializedName("title")
    var title : String?="",
    @SerializedName("gambar")
    var gambar : Int?= 0,
    @SerializedName("lokasi")
    var lokasi : String?= "",
    @SerializedName("harga")
    var harga : String?= ""
)
