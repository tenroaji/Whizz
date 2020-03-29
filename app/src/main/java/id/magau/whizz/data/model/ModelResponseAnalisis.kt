package id.magau.whizz.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Andi Tenroaji Ahmad on 11/18/2019.
 */

data class ModelResponseAnalisis(
    @SerializedName("diagnostic")
    var diagnostic: ModelDiagnostic? = null,
    @SerializedName("response")
    var response: ModelAnalisis? = null
)


data class ModelAnalisis(
    @SerializedName("history")
    var history: List<History>,
    @SerializedName("total")
    var total: Int?=0,
    @SerializedName("twk")
    var twk: Int?=0,
    @SerializedName("tiu")
    var tiu: Int?=0,
    @SerializedName("tkp")
    var tkp: Int?=0,
    @SerializedName("benar")
    var benar: Int?=0,
    @SerializedName("salah")
    var salah: Int?=0,
    @SerializedName("pass")
    var pass: Int?=0,
    @SerializedName("status")
    var status: Int?=0,
    @SerializedName("percent")
    var percent: Double? = 0.00,
    @SerializedName("waktu")
    var waktu: String? ="",
    @SerializedName("twk_statistic")
    var twk_statistic: List<ModelStatistic>?=null,
    @SerializedName("tiu_statistic")
    var tiu_statistic: List<ModelStatistic>?=null,
    @SerializedName("tkp_statistic")
    var tkp_statistic: List<ModelStatistic>?=null
)

data class History(
    @SerializedName("pilihan")
    var pilihan: String? ="",
    @SerializedName("bobot")
    var bobot: Int?=0
):Serializable

data class ModelStatistic(
    @SerializedName("sub") var sub: String? ="",
    @SerializedName("jumlah") var jumlah: Int?=0,
    @SerializedName("hasil") var hasil: Int?=0
)