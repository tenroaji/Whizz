package id.magau.whizz.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

data class ModelDiagnostic(
    @SerializedName("code")
    var code : Int?=0,
    @SerializedName("status")
    var status : String?= ""
)