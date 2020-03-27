package id.magau.whizz.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Andi Tenroaji Ahmad on 3/23/2020.
 */

data class ModelResponseUjian (
    @SerializedName("diagnostic")
    var diagnostic : ModelDiagnostic? = null,
    @SerializedName("response")
    var response : ModelUjian? = null
)

data class ModelUjian (
    @SerializedName("title")
    var title : String? = "")