package id.magau.whizz.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Andi Tenroaji Ahmad on 11/18/2019.
 */

data class ModelResponseTestimoni (
    @SerializedName("diagnostic")
    var diagnostic : ModelDiagnostic? = null,
    @SerializedName("response")
    var response : ModelTestimoni? = null
)


data class ModelTestimoni(
    @SerializedName("nama")
    var nama : String?="",
     @SerializedName("pekerjaan")
    var pekerjaan : String?="",
     @SerializedName("foto")
    var foto : String?="",
    @SerializedName("desc")
    var desc : String?=""
)
