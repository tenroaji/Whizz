package id.magau.whizz.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Andi Tenroaji Ahmad on 11/18/2019.
 */

data class ModelResponseFile (
    @SerializedName("diagnostic")
    var diagnostic : ModelDiagnostic? = null,
    @SerializedName("response")
    var response : ArrayList<ModelFile?>? = null
)


data class ModelFile(
    @SerializedName("title")
    var title : String?="",
    @SerializedName("path")
    var path : String?=""

)
