package id.magau.whizz.ui.skill_detail

import android.content.Context
import com.google.gson.Gson
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelResponseMainMenu
import id.magau.whizz.data.model.ModelResponseSkills
import id.magau.whizz.data.model.ModelResponseSkillsDetail
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

class SkillDetailPresenter(val context: Context, val view: SkillDetailContracts.View) :
    SkillDetailContracts.Presenter {
    private val mService: SkillsApiRoute = RetrofitUtils.createService(
        context.resources.getString(R.string.base_url),
        SkillsApiRoute::class.java,
        30000L, HttpLoggingInterceptor.Level.HEADERS
    )
    private var mToken =""
    init {
        view.setPresenter(this)
        val session = SessionUtils(context)
        mToken = session.getData(PREF_KEY_TOKEN, "")
    }

    override fun loadData(idProduct: String) {
        view.showLoading(true)
        mService.detailSkill(mToken,idProduct).apply {
            enqueue(object : Callback<ModelResponseSkillsDetail> {
                override fun onFailure(call: Call<ModelResponseSkillsDetail>, t: Throwable) {
                    view.showLoading(false)
                    view.showError(0, "Internal Server Error")
                }

                override fun onResponse(
                    call: Call<ModelResponseSkillsDetail>,
                    response: Response<ModelResponseSkillsDetail>
                ) {
                    view.showLoading(false)
                    if (response.code() == 200) {
                        val data = response.body()?.response
                        data?.let{
                            view.showSkill(it)
                        }
                    } else if (response.code() == 500) {
                        view.showError(500, "Internal Server Error")
                    } else {
                        //http code selain 200
                        response.errorBody()?.run {
                            val diagnostic = Gson().fromJson(
                                this.toString(),
                                ModelResponseSkillsDetail::class.java
                            )
                            view.showError(
                                diagnostic.diagnostic?.code!!,
                                diagnostic.diagnostic?.status
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