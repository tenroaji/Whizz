package id.magau.whizz.ui.event

import android.content.Context
import com.google.gson.Gson
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelEvents
import id.magau.whizz.data.model.ModelResponseDiagnostic
import id.magau.whizz.data.model.ModelResponseSkills
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

class EventPresenter(val context: Context, val mView: EventContracts.View) :
    EventContracts.Presenter {
    private val mService: SkillsApiRoute = RetrofitUtils.createService(
        context.resources.getString(R.string.base_url),
        SkillsApiRoute::class.java,
        30000L, HttpLoggingInterceptor.Level.HEADERS
    )
    private var mToken =""
    private var nextPage = 1
    private var isLoading = false
    private var mData: MutableList<ModelEvents?>? = mutableListOf()

    init {
        mView.setPresenter(this)
        val session = SessionUtils(context)
        mToken = session.getData(PREF_KEY_TOKEN, "")
    }


    override fun loadEvent() {
        if (isLoading) return
        isLoading = true
        if (nextPage == 1) {
            mView.showLoading(true)
        } else {
            mView.showNextLoading(true)
        }

        mService.allSkill(mToken).apply {
            enqueue(object : Callback<ModelResponseSkills> {
                override fun onFailure(call: Call<ModelResponseSkills>, t: Throwable) {
                    if (nextPage == 1 ){
                        mView.showLoading(false)
                    }else {
                        mView.showNextLoading(false)
                    }

                    mView.showError(0, "Internal Server Error")
                }

                override fun onResponse(
                    call: Call<ModelResponseSkills>,
                    response: Response<ModelResponseSkills>
                ) {
                    mView.showLoading(false)
                    if (response.code() == 200) {
                        val data = response.body()?.response
                        data?.let{
//                            mView.showEvent(it)
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

    override fun isCanNextPage(): Boolean {
        return nextPage > 1 && !isLoading
    }

    override fun forceUpdate() {
        nextPage = 1
        isLoading = false
        mData?.clear()
    }



    override fun start() {
        loadEvent()
    }

}