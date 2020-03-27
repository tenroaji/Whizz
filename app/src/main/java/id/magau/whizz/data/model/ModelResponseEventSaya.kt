package id.magau.whizz.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Andi Tenroaji Ahmad on 11/18/2019.
 */

data class ModelResponseEventSaya (
    @SerializedName("diagnostic")
    var diagnostic : ModelDiagnostic? = null,
    @SerializedName("response")
    var response : ModelEventSaya? = null
)


data class ModelEventSaya(
    @SerializedName("title")
    var title : String?="",
    @SerializedName("gambar")
    var gambar : Int?= 0,
    @SerializedName("tanggal")
    var tanggal : String?= ""
)
