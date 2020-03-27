package id.magau.whizz.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Andi Tenroaji Ahmad on 3/23/2020.
 */

data class ModelResponseDiskusi(
    @SerializedName("foto")
    var foto: String? = "",
    @SerializedName("nama   ")
    var nama: String? = "",
    @SerializedName("tanggal")
    var tanggal: String? = "",
    @SerializedName("desc")
    var desc: String? = "",
    @SerializedName("foto1")
    var foto1: String? = "",
    @SerializedName("foto2")
    var foto2: String? = "",
    @SerializedName("balasan")
    var balasan: String? = "",
    @SerializedName("foto3")
    var foto3: String? = ""
)