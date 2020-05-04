package id.magau.whizz.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Andi Tenroaji Ahmad on 3/23/2020.
 */

data class ModelResponseHasilAnalisis (
    @SerializedName("diagnostic")
    var diagnostic : ModelDiagnostic? = null,
    @SerializedName("response")
    var response : ModelHasilAnalisis? = null
)


data class ModelHasilAnalisis(
    @SerializedName("jumlah_benar")
    var jumlah_benar : Int?=0,
    @SerializedName("jumlah_salah")
    var jumlah_salah : Int?=0,
    @SerializedName("jumlah_dilewati")
    var jumlah_dilewati : Int?=0,
    @SerializedName("skors")
    var skors : Int?=0,
    @SerializedName("ket")
    var ket : String?=""
)