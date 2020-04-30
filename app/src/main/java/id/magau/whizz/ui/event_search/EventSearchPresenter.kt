package id.magau.whizz.ui.event_search

import android.content.Context
import com.google.gson.Gson
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelEvents
import id.magau.whizz.data.model.ModelResponseDiagnostic
import id.magau.whizz.data.model.ModelResponseEvent
import id.magau.whizz.data.services.EventApiRoute
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

class EventSearchPresenter(val context: Context, val mView: EventSearchContracts.View) :
    EventSearchContracts.Presenter {
    private val mService: EventApiRoute = RetrofitUtils.createService(
        context.resources.getString(R.string.base_url),
        EventApiRoute::class.java,
        30000L, HttpLoggingInterceptor.Level.HEADERS
    )
    private var mToken = ""
    private var nextPage = 1
    private var isLoading = false
    private var mData: ArrayList<ModelEvents?> = arrayListOf()

    init {
        mView.setPresenter(this)
        val session = SessionUtils(context)
        mToken = session.getData(PREF_KEY_TOKEN, "")
    }



    override fun searchEvent(event: String?) {
        if (isLoading) return
        isLoading = true
        if (nextPage == 1) {
            mView.showLoading(true)
        } else {
            mView.showNextLoading(true)
        }
        mService.searchEvent(mToken,event).apply {
            enqueue(object : Callback<ModelResponseEvent> {
                override fun onFailure(call: Call<ModelResponseEvent>, t: Throwable) {
                    if (nextPage == 1) {
                        mView.showLoading(false)
                    } else {
                        mView.showNextLoading(false)
                    }
                    mView.showError(0, "Internal Server Error $t")
                    isLoading = false
                }

                override fun onResponse(
                    call: Call<ModelResponseEvent>,
                    response: Response<ModelResponseEvent>
                ) {
                    val pagination = response.body()?.pagination
                    if (nextPage == 1) {
                        mView.showLoading(false)
                    } else {
                        mView.showNextLoading(false)
                    }
                    isLoading = false
                    if (response.code() == 200) {
                        val data = response.body()?.response
                        if (pagination != null) {
                            nextPage = if (pagination.current_page!! < pagination.last_page!!) pagination.current_page!! + 1 else -1
                            mData.addAll(data as ArrayList<ModelEvents?>)
                            if (pagination.current_page == 1) {
                                data.let {
                                    mView.showData(it)
                                }
                            } else {
                                if (!mData.isNullOrEmpty()) mView.showData(mData)
                                else mView.showNoData()
                            }
                        }
                        else {
                            data?.let {
                                mView.showData(it)
                            }
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
        mData.clear()
    }


    override fun start() {
    }

}