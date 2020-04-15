package id.magau.whizz.ui.kelas.materi

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import id.magau.whizz.R
import id.magau.whizz.data.model.*
import id.magau.whizz.data.services.SkillsApiRoute
import id.magau.whizz.utils.RetrofitUtils
import id.magau.whizz.utils.SessionUtils
import id.magau.whizz.utils.SessionUtils.Companion.PREF_KEY_TOKEN
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

class MateriPresenter(val context: Context, val mView: MateriContracts.View) :
    MateriContracts.Presenter {
//    private val mService: SkillsApiRoute = RetrofitUtils.createService(
//        context.resources.getString(R.string.base_url),
//        SkillsApiRoute::class.java,
//        30000L, HttpLoggingInterceptor.Level.HEADERS
//    )


    private var mToken =""
    init {
        mView.setPresenter(this)
        val session = SessionUtils(context)
        mToken = session.getData(PREF_KEY_TOKEN, "")
    }

    override fun loadData(idProduct: String) {

        val typeCourse = GsonBuilder().let {
            it.registerTypeAdapter(
                ResponseModel::class.java,
                CourseTypeModelDeserializer()
            )
            it.create()
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(context.resources.getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create(typeCourse))
            .build()
        val mService = retrofit.create(SkillsApiRoute::class.java)

        mView.showLoading(true)
        mService.getMateri(mToken,idProduct).apply {
            enqueue(object : Callback<ResponseModel> {
                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    mView.showLoading(false)
                    mView.showError(0, "Internal Server Error $t")
                }

                override fun onResponse(
                    call: Call<ResponseModel>,
                    response: Response<ResponseModel>
                ) {

                    mView.showLoading(false)
                    if (response.code() == 200) {
                        val data = response.body()?.response
                        data?.let{
                            mView.showData(it)
                        }
//                        val jsonString = response.body().toString()
//                        if (jsonString.contains("data:")) {
//                           val data = Gson().fromJson(jsonString, ResponseModel::class.java)
//                        } else {
//                            val error = Gson().fromJson(jsonString, Error::class.java)
//                        }
                    } else if (response.code() == 500) {
                        mView.showError(500, "Internal Server Error")
                    } else {
                        //http code selain 200
                        response.errorBody()?.string()?.run {
                            val model = Gson().fromJson(
                                this,
                                ModelResponseDiagnostic::class.java
                            )
                            mView.showError(
                                model.diagnostic.code,
                                model.diagnostic.status
                            )
                        }
                    }
                }
            })
        }
    }

    override fun start() {
    }

}