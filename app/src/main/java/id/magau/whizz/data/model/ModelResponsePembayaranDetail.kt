package id.magau.whizz.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Andi Tenroaji Ahmad on 11/18/2019.
 */

data class ModelResponsePembayaranDetail (
    @SerializedName("diagnostic")
    var diagnostic : ModelDiagnostic? = null,
    @SerializedName("response")
    var response : ModelPembayaranDetail? = null
)


data class ModelPembayaranDetail(
    @SerializedName("nota")
    var nota : String?="",
    @SerializedName("no_va")
    var no_va : String?="",
    @SerializedName("no_reverensi")
    var no_reverensi : String?=""
)
