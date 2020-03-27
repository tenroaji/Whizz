package id.magau.whizz.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Andi Tenroaji Ahmad on 11/18/2019.
 */

data class ModelResponseFAQ (
    @SerializedName("diagnostic")
    var diagnostic : ModelDiagnostic? = null,
    @SerializedName("response")
    var response : ModelFAQ? = null
)


data class ModelFAQ(
    @SerializedName("title")
    var title : String?="",
    @SerializedName("desc")
    var desc : String?=""
)
