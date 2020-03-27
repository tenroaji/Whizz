package id.magau.whizz.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

data class ModelPagination(
    @SerializedName("total")
    var total : Int?=0,
    @SerializedName("per_page")
    var per_page : Int?= 0,
    @SerializedName("current_page")
    var current_page : Int?= 0,
    @SerializedName("last_page")
    var last_page : Int?= 0
)
