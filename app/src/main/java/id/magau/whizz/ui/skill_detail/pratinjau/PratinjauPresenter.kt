package id.magau.whizz.ui.skill_detail.pratinjau

import android.content.Context
import com.google.gson.Gson
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

/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

class PratinjauPresenter(val context: Context, val mView: PratinjauContracts.View) :
    PratinjauContracts.Presenter {
    private val mService: SkillsApiRoute = RetrofitUtils.createService(
        context.resources.getString(R.string.base_url),
        SkillsApiRoute::class.java,
        30000L, HttpLoggingInterceptor.Level.HEADERS
    )
    private var mToken =""
    init {
        mView.setPresenter(this)
        val session = SessionUtils(context)
        mToken = session.getData(PREF_KEY_TOKEN, "")
    }

    override fun loadData(idProduct: String) {
//        mView.showLoading(true)
//        mService.detailSkill(mToken,idProduct).apply {
//            enqueue(object : Callback<ModelResponseFAQ> {
//                override fun onFailure(call: Call<ModelResponseFAQ>, t: Throwable) {
//                    mView.showLoading(false)
//                    mView.showError(0, "Internal Server Error")
//                }
//
//                override fun onResponse(
//                    call: Call<ModelResponseFAQ>,
//                    response: Response<ModelResponseFAQ>
//                ) {
//                    mView.showLoading(false)
//                    if (response.code() == 200) {
//                        val data = response.body()?.response
//                        data?.let{
//                            mView.showSkill(it)
//                        }
//                    } else if (response.code() == 500) {
//                        mView.showError(500, "Internal Server Error")
//                    } else {
//                        //http code selain 200
//                        response.errorBody()?.run {
//                            val diagnostic = Gson().fromJson(
//                                this.toString(),
//                                ModelResponseFAQ::class.java
//                            )
//                            mView.showError(
//                                diagnostic.diagnostic?.code!!,
//                                diagnostic.diagnostic?.status
//                            )
//                        }
//                    }
//                }
//            })
//        }
    }


    override fun loadFAQ(idProduct: String) {
        mView.showLoading(true)
        mService.dataFAQ(mToken,idProduct).apply {
            enqueue(object : Callback<ModelResponseFAQ> {
                override fun onFailure(call: Call<ModelResponseFAQ>, t: Throwable) {
                    mView.showLoading(false)
                    mView.showError(0, "Internal Server Error")
                }

                override fun onResponse(
                    call: Call<ModelResponseFAQ>,
                    response: Response<ModelResponseFAQ>
                ) {
                    mView.showLoading(false)
                    if (response.code() == 200) {
                        val data = response.body()?.response
                        data?.let{
                            mView.showFAQ(it)
                        }
                    } else if (response.code() == 500) {
                        mView.showError(500, "Internal Server Error")
                    } else {
                        //http code selain 200
                        response.errorBody()?.string().run {
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

    override fun loadTestimoni(idProduct: String) {
    }

    override fun start() {
    }

}