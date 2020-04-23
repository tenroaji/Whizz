package id.magau.whizz.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Andi Tenroaji Ahmad on 11/18/2019.
 */

data class ModelResponseSkills (
    @SerializedName("diagnostic")
    var diagnostic : ModelDiagnostic? = null,
    @SerializedName("pagination")
    var pagination: ModelPagination? = null,
    @SerializedName("response")
    var response : ArrayList<ModelProducts?>? = null
)


