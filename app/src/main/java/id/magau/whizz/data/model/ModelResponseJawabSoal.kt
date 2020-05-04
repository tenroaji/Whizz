package id.magau.whizz.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Andi Tenroaji Ahmad on 11/18/2019.
 */

data class ModelResponseJawabSoal (
    @SerializedName("diagnostic")
    var diagnostic : ModelDiagnostic? = null,
    @SerializedName("response")
    var response : Boolean?= false
)

data class ModelSendExam(
    @SerializedName("uuid_soal")
    var uuid_soal : String? = "",
    @SerializedName("answer")
    var answer : String?= ""
)


