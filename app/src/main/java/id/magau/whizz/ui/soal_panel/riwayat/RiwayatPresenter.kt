package id.magau.whizz.ui.soal_panel.riwayat

import android.content.Context
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelResponseRiwayat
import id.magau.whizz.data.model.ModelRiwayat
import id.magau.whizz.data.services.RiwayatApiRoute
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

class RiwayatPresenter(val context: Context, val view: RiwayatContracts.View) :
    RiwayatContracts.Presenter {
    private val mService: RiwayatApiRoute = RetrofitUtils.createService(
        context.resources.getString(R.string.base_url),
        RiwayatApiRoute::class.java,
        30000L,
        HttpLoggingInterceptor.Level.HEADERS
    )
    private var mToken: String = ""
    private var nextPage = 1
    private var isLoading = false

    init {
        view.setPresenter(this)
        val session = SessionUtils.getInstance(context)
        mToken = session.getData(PREF_KEY_TOKEN, "")
    }

    private var mData: MutableList<ModelRiwayat?>? = mutableListOf()
    override fun getRiwayat(idProduct: String) {
        if (isLoading) return
        isLoading = true
        if (nextPage == 1) {
            view.showLoading(true)
        } else {
            view.showNextLoading(true)
        }

        mService.getRiwayat(mToken, idProduct, nextPage.toString())
            .enqueue(object : Callback<ModelResponseRiwayat> {
                override fun onFailure(call: Call<ModelResponseRiwayat>, t: Throwable) {
                    isLoading = false
                    if (nextPage == 1) {
                        view.showLoading(false)
                    } else {
                        view.showNextLoading(false)
                    }
                    view.showError(0, "Server Internal Error $t")
                }

                override fun onResponse(
                    call: Call<ModelResponseRiwayat>,
                    response: Response<ModelResponseRiwayat>
                ) {
                    val pagination = response.body()?.pagination
                    val data = response.body()?.response
                    if (nextPage == 1) {
                        view.showLoading(false)
                    } else {
                        view.showNextLoading(false)
                    }
                    isLoading = false
                    if (!data.isNullOrEmpty()) {
                        if (pagination != null) {
                            nextPage = if (pagination.current_page!! < pagination.last_page!!)
                                pagination.current_page!! + 1 else -1
                            mData?.addAll(data as List<ModelRiwayat>)
                            if (pagination.current_page == 1) {
                                view.showRiwayat(data)
                            } else {
                                if (!mData.isNullOrEmpty())view.showRiwayat(mData!!)
                                else view.showNoData()
                            }
                        }
                    } else {
                        view.showNoData()
                    }
                }
            })
    }

    override fun forceUpdate() {
        nextPage = 1
        isLoading = false
        mData?.clear()
    }

    override fun isCanNextPage(): Boolean {
        return nextPage > 1 && !isLoading
    }

    override fun start() {

    }

}

