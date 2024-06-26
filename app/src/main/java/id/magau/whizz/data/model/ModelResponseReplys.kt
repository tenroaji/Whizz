package id.magau.whizz.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Andi Tenroaji Ahmad on 11/18/2019.
 */

data class ModelResponseReplys (
    @SerializedName("diagnostic")
    var diagnostic : ModelDiagnostic? = null,
    @SerializedName("response")
    var response : ArrayList<ModelReplys?>? = null
)


data class ModelReplys(
    @SerializedName("user")
    var user : ModelTeacher?= null,
    @SerializedName("reply")
    var reply : String?= ""
)
