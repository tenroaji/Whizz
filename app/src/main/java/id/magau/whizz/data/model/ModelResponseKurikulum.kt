package id.magau.whizz.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Andi Tenroaji Ahmad on 11/18/2019.
 */

data class ModelResponseKurikulum (
    @SerializedName("diagnostic")
    var diagnostic : ModelDiagnostic? = null,
    @SerializedName("response")
    var response : ModelKurikulum? = null
)


data class ModelKurikulum(
    @SerializedName("no")
    var no : String?="",
    @SerializedName("desc")
    var desc : String?= ""
)
