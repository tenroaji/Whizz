package id.magau.whizz.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Andi Tenroaji Ahmad on 11/18/2019.
 */

data class ModelResponseComments (
    @SerializedName("diagnostic")
    var diagnostic : ModelDiagnostic? = null,
    @SerializedName("response")
    var response : ArrayList<ModelComments?>? = null
)


data class ModelComments(
    @SerializedName("uuid_comment")
    var uuid_comment : String?="",
    @SerializedName("comment")
    var comment : String?="",
    @SerializedName("user")
    var user : ModelTeacher?= null,
    @SerializedName("reply")
    var reply : ArrayList<ModelReply>?= null
)

data class ModelReply(
    @SerializedName("reply")
    var reply :String? = ""
)