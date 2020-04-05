package id.magau.whizz.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Andi Tenroaji Ahmad on 11/18/2019.
 */

data class ModelResponseCheckOut (
    @SerializedName("diagnostic")
    var diagnostic : ModelDiagnostic? = null,
    @SerializedName("response")
    var response : ArrayList<ModelCheckOut?>? = null
)


data class ModelCheckOut(
    @SerializedName("course")
    var course : ModelCheckOutCourse?=null
)


data class ModelCheckOutCourse(
    @SerializedName("uuid_course")
    var uuid_course : String?="",
    @SerializedName("price")
    var price : Int?=0
)

