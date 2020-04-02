package id.magau.whizz.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Andi Tenroaji Ahmad on 11/18/2019.
 */

data class ModelResponseKurikulum(
    @SerializedName("diagnostic")
    var diagnostic: ModelDiagnostic? = null,
    @SerializedName("response")
    var response: ArrayList<ModelKurikulum?>? = null
)


data class ModelKurikulum(
    @SerializedName("no")
    var no: String? = "",
    @SerializedName("title_section")
    var title: String? = "",
    @SerializedName("sub_section")
    var subSection: ArrayList<ModelSubSection?>? = null
)
data class ModelSubSection(
    @SerializedName("title")
    var title: String? = ""
)
