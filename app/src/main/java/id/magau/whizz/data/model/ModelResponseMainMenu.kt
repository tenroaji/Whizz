package id.magau.whizz.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

/**
 * Created by Andi Tenroaji Ahmad on 11/18/2019.
 */

data class ModelResponseMainMenu (
    @SerializedName("diagnostic")
    var diagnostic : ModelDiagnostic? = null,
    @SerializedName("response")
    var response : ModelMainMenu? = null
)


data class ModelMainMenu(
    @SerializedName("course_count")
    var course_count : Int?= 0,
    @SerializedName("products")
    var products : ArrayList<ModelProducts?>?=null,
    @SerializedName("events")
    var events : ArrayList<ModelEvents?>?=null,
    @SerializedName("products_promo")
    var products_promo : ArrayList<ModelProducts?>?=null
)

data class ModelProducts(
    @SerializedName("id")
    var id : Int? = 0,
    @SerializedName("uuid_course")
    var uuid_course : String? = "",
    @SerializedName("teacher")
    var teacher : ModelTeacher? = null,
    @SerializedName("title")
    var title : String? = "",
    @SerializedName("desc")
    var desc : String? = "",
    @SerializedName("price")
    var price : Int? = 0,
    @SerializedName("category")
    var category : ModelCategory? = null,
    @SerializedName("rate")
    var rate : Double? = 0.0,
    @SerializedName("image")
    var image : String? = "",
    @SerializedName("is_mine")
    var is_mine : Boolean? = false,
    @SerializedName("promo")
    var promo : ModelPromoProduct? = null,
    @SerializedName("streaming")
    var streaming : ArrayList<ModelStreaming?>? = null
)

@Parcelize
data class ModelStreaming(
    @SerializedName("title")
    var title : String? = "",
    @SerializedName("link")
    var link : String? = "",
    @SerializedName("waktu")
    var waktu : String? = "",
    @SerializedName("tanggalWeb")
    var tanggalWeb : String? = "",
    @SerializedName("waktuWeb")
    var waktuWeb : String? = ""
):Parcelable

data class ModelPromoProduct(
    @SerializedName("is_approve")
    var is_approve : Boolean? = false,
    @SerializedName("is_percent")
    var is_percent : Boolean? = false,
    @SerializedName("value")
    var value : Int? = 0
)

data class ModelCategory(
    @SerializedName("category")
    var category : String? = ""
)
data class ModelEvents(
    @SerializedName("id")
    var id : Int? = 0,
    @SerializedName("uuid_events")
    var uuid_events : String? = "",
    @SerializedName("owner")
    var owner : ModelTeacher? = null,
    @SerializedName("event")
    var event : String? = "",
    @SerializedName("harga")
    var harga : Int? = 0,
    @SerializedName("kota")
    var kota : String? = "",
    @SerializedName("alamat")
    var alamat : String? = "",
    @SerializedName("no_hp")
    var no_hp : String? = "",
    @SerializedName("tanggal")
    var tanggal : String? = "",
    @SerializedName("tanggalWeb")
    var tanggalWeb : String? = "",
    @SerializedName("waktuWeb")
    var waktuWeb : String? = "",
    @SerializedName("rate")
    var rate : Double? = 0.0,
    @SerializedName("image")
    var image : String? = "",
    @SerializedName("pemateri")
    var pemateri : List<ModelPemateri>
)

data class ModelPemateri(
    @SerializedName("nama_pemateri")
    var nama : String? = "",
    @SerializedName("job_pemateri")
    var job_title : String? = "",
    @SerializedName("gambar")
    var gambar : String? = "",
    @SerializedName("desc")
    var desc : String? = ""
):Serializable

data class ModelTeacher(
    @SerializedName("name")
    var name : String? = "",
    @SerializedName("email")
    var email : String? = ""
)
