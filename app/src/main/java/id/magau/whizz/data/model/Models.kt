package id.magau.whizz.data.model

import com.google.gson.annotations.SerializedName

data class DiagnosticModel(
    val code: Int = -1,
    val status: String = "UNKNOWN"
)

data class SectionModel(
    val id: String,
    @SerializedName("uuid_course") val uuid: String,
    @SerializedName("title_section") val sectionTitle: String,
    @SerializedName("sub_section") val subSections: List<CourseTypeModel>,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
)

data class CourseHtmlModel(
    val id: String,
    val title: String,
    @SerializedName("materi") val course: CourseHtmlItemModel
) : CourseTypeModel()

data class CoursePdfModel(
    val id: String,
    val title: String,
    @SerializedName("materi") val course: CoursePdfItemModel
) : CourseTypeModel()

data class CourseVideoModel(
    val id: String,
    val title: String,
    @SerializedName("materi") val course: CourseVideoItemModel
) : CourseTypeModel()

data class CourseExamModel(
    val id: String,
    val uuid_sub_section: String,
    val title: String,
    @SerializedName("materi") val course: ArrayList<ModelHistoriJawaban?>?
) : CourseTypeModel()

data class CourseHtmlItemModel(
    val id: Int,
    @SerializedName("html") val rawHtml: String,
    val title: String
)

data class CoursePdfItemModel(
    val id: Int,
    @SerializedName("pdf") val fileUrl: String,
    val title: String,
    val description: String
)

data class CourseVideoItemModel(
    val id: Int,
    val title: String,
    @SerializedName("video") val videos: List<VideoItemModel>,
    @SerializedName("desc") val description: String
)

data class CourseExamItemModel(
    val id: Int,
    @SerializedName("soal") val question: String,
    @SerializedName("jawaban") val answer: String,
    @SerializedName("uuid") val uuid: String,
    val A: String,
    val B: String,
    val C: String,
    val D: String,
    val E: String
)

data class VideoItemModel(
    @SerializedName("ukuran") val size: String,
    val link: String
)

data class ResponseModel(
    val diagnostic: DiagnosticModel,
    val response: List<SectionModel>
)