package id.magau.whizz.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Andi Tenroaji Ahmad on 11/18/2019.
 */

data class ModelResponseSoal (
    @SerializedName("diagnostic")
    var diagnostic : ModelDiagnostic? = null,
    @SerializedName("response")
    var response : ModelSoal? = null
)

data class ModelSoal(
    @SerializedName("history")
    var history: ModelHistory? = null,
    @SerializedName("total")
    var total: Int?= 0,
    @SerializedName("diff")
    var diff: String?= "",
    @SerializedName("now_date")
    var now_date: String?= "",
    @SerializedName("historiJawaban")
    var historiJawaban: MutableList<ModelHistoriJawaban>?= null
)

data class ModelHistoriJawaban (
    @SerializedName("id")
    var id: Int?= 0,
    @SerializedName("pilihan")
    var pilihan: String?= "",
    @SerializedName("id_soal")
    var id_soal: Int?= 0,
    @SerializedName("gambar_soal")
    var gambar_soal: String?= "",
    @SerializedName("soal")
    var soal: String?= "",
    @SerializedName("a")
    var a: String?= "",
    @SerializedName("gambar_a")
    var gambar_a: String?= "",
    @SerializedName("b")
    var b: String?= "",
    @SerializedName("gambar_b")
    var gambar_b: String?= "",
    @SerializedName("c")
    var c: String?= "",
    @SerializedName("gambar_c")
    var gambar_c: String?= "",
    @SerializedName("d")
    var d: String?= "",
    @SerializedName("gambar_d")
    var gambar_d: String?= "",
    @SerializedName("e")
    var e: String?= "",
    @SerializedName("gambar_e")
    var gambar_e: String?= "",

    @SerializedName("bobot")
    var bobot: Int?= 0,
    @SerializedName("jawabanDiPilih")
    var jawabanDiPilih: ModelJawaban?= null,

    @SerializedName("jawaban")
    var jawaban: ModelJawaban?= null,

    @SerializedName("pembahasan")
    var pembahasan: String?= "",
    @SerializedName("gambarPembahasan")
    var gambarPembahasan: String?= "",

    @SerializedName("jenis")
    var jenis: String?= "",
    var active: Boolean? = false
):Serializable


data class ModelJawaban(
    @SerializedName("urutan")
    var urutan: String?= "",
    @SerializedName("soal")
    var soal: String?= "",

    @SerializedName("jawaban")
    var jawaban: String?= "",

    @SerializedName("gambar")
    var gambar: String?= "",
    @SerializedName("bobot")
    var bobot: String?= ""
)
data class ModelHistory (
    @SerializedName("id")
    var id: Int?= 0,
    @SerializedName("uuid")
    var uuid: String?= "",
    @SerializedName("twk_count")
    var twk_count: Int?= 0,
    @SerializedName("tiu_count")
    var tiu_count: Int?= 0,
    @SerializedName("tkp_count")
    var tkp_count: Int?= 0,
    @SerializedName("end_tryout")
    var end_tryout: String?= ""
)