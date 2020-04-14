package id.magau.whizz.ui.event_detail

import android.content.Context
import com.google.gson.Gson
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelEvents
import id.magau.whizz.data.model.ModelResponseDiagnostic
import id.magau.whizz.data.model.ModelResponseEventDetail
import id.magau.whizz.data.services.EventApiRoute
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

class EventDetailPresenter(val context: Context, val mView: EventDetailContracts.View) :
    EventDetailContracts.Presenter {
    private val mService: EventApiRoute = RetrofitUtils.createService(
        context.resources.getString(R.string.base_url),
        EventApiRoute::class.java,
        30000L, HttpLoggingInterceptor.Level.HEADERS
    )
    private var mToken = ""

    init {
        mView.setPresenter(this)
        val session = SessionUtils(context)
        mToken = session.getData(PREF_KEY_TOKEN, "")
    }


    override fun loadEvent(idEvent: String) {
        mView.showLoading(true)

        mService.getEventDetail(mToken, idEvent).apply {
            enqueue(object : Callback<ModelResponseEventDetail> {
                override fun onFailure(call: Call<ModelResponseEventDetail>, t: Throwable) {
                    mView.showLoading(false)
                    mView.showError(0, "Internal Server Error")
                }

                override fun onResponse(
                    call: Call<ModelResponseEventDetail>,
                    response: Response<ModelResponseEventDetail>
                ) {
                    mView.showLoading(false)
                    if (response.code() == 200) {
                        val data = response.body()?.response
                        data?.let {
                            mView.showEvent(it)
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



    override fun start() {
    }

}