package id.magau.whizz.data.model

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import java.lang.reflect.Type
import java.util.*

abstract class CourseTypeModel {

    enum class CourseValueType constructor(val nativeInt: Int) {
        html(1),
        pdf(2),
        video(3),
        exam(4)
    }

    @SerializedName("type")
    private lateinit var _type: CourseValueType

    open val type: CourseValueType
        get() = _type
}

class CourseTypeModelDeserializer : JsonDeserializer<ResponseModel> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ResponseModel? {
        return json?.let {
            val gson = Gson()
            val jsonObject = it.asJsonObject
            val diagnostic = gson.fromJson(
                jsonObject.get("diagnostic"),
                DiagnosticModel::class.java
            )
            val responseElement = jsonObject.get("response").asJsonArray.map { section ->
                val sectionObject = section.asJsonObject
                val courses: List<CourseTypeModel> =
                    sectionObject.get("sub_section").asJsonArray.map { sub ->
                        val type = CourseTypeModel.CourseValueType.valueOf(
                            sub.asJsonObject.get("type").asString.toLowerCase(Locale.getDefault())
                        )
                        when (type) {
                            CourseTypeModel.CourseValueType.pdf -> gson.fromJson(
                                sub,
                                CoursePdfModel::class.java
                            )
                            CourseTypeModel.CourseValueType.html -> gson.fromJson(
                                sub,
                                CourseHtmlModel::class.java
                            )
                            CourseTypeModel.CourseValueType.video -> gson.fromJson(
                                sub,
                                CourseVideoModel::class.java
                            )
                            CourseTypeModel.CourseValueType.exam -> gson.fromJson(
                                sub,
                                CourseExamModel::class.java
                            )
                            else -> gson.fromJson(sub, CourseHtmlModel::class.java)
                        }
                    }
                SectionModel(
                    sectionObject.get("id").asString,
                    sectionObject.get("uuid_course").asString,
                    sectionObject.get("title_section").asString,
                    courses,
                    sectionObject.get("created_at").asString,
                    sectionObject.get("updated_at").asString
                )
            }

            ResponseModel(
                diagnostic,
                responseElement
            )
        }
    }
}