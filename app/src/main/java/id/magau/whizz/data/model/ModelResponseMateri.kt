package id.magau.whizz.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Andi Tenroaji Ahmad on 11/18/2019.
 */

data class ModelResponseMateri (
    @SerializedName("diagnostic")
    var diagnostic : ModelDiagnostic? = null,
    @SerializedName("response")
    var response : ArrayList<ModelMateri?>? = null
)


data class ModelMateri(
    @SerializedName("uuid_course")
    var uuid_course : String?="",
    @SerializedName("title_section")
    var title_section : String?= "",
    @SerializedName("sub_section")
    var sub_section : ArrayList<ModelSubSectionMateri?>?= null
)

data class ModelSubSectionMateri(
    @SerializedName("id")
    var id : Int?=0,
    @SerializedName("title")
    var title : String?= "",
    @SerializedName("type")
    var type : String?= "",
    @SerializedName("materi")
    var materi : ModelSubMateri?= null
)

data class ModelSubMateri(
    @SerializedName("id")
    var id : Int?=0,
    @SerializedName("video")
    var video : String?= "",
    @SerializedName("title")
    var title : String?= "",
    @SerializedName("desc")
    var desc : String?= "",
    @SerializedName("pdf")
    var pdf : String?= "",
    @SerializedName("description")
    var description : String?= ""
)