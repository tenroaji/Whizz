package id.magau.whizz.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Andi Tenroaji Ahmad on 11/18/2019.
 */

data class ModelResponseRiwayat(
    @SerializedName("diagnostic")
    var diagnostic: ModelDiagnostic? = null,
    @SerializedName("pagination")
    var pagination: ModelPagination? = null,
    @SerializedName("response")
    var response: MutableList<ModelRiwayat?>? = null
)


data class ModelRiwayat(
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("uuid")
    var uuid: String? = "",
    @SerializedName("end_tryout")
    var end_tryout: String? = "",
    @SerializedName("online")
    var online: String? = "",
    @SerializedName("status")
    var status: String? = ""
)
