package id.magau.whizz.ui.pembayaran

import android.content.Context
import com.google.gson.Gson
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelResponsePembayaran
import id.magau.whizz.data.services.PembayaranApiRoute
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

class PembayaranPresenter(val context: Context, val view: PembayaranContracts.View) :
    PembayaranContracts.Presenter {
    private val mService: PembayaranApiRoute = RetrofitUtils.createService(
        context.resources.getString(R.string.base_url),
        PembayaranApiRoute::class.java,
        30000L, HttpLoggingInterceptor.Level.HEADERS
    )
    private var mToken = ""
    init {
        view.setPresenter(this)
        val session = SessionUtils(context)
        mToken = session.getData(PREF_KEY_TOKEN, "")
    }


    override fun loadData() {
        view.showLoading(true)
        mService.bank(mToken).apply {
            enqueue(object : Callback<ModelResponsePembayaran> {
                override fun onFailure(call: Call<ModelResponsePembayaran>, t: Throwable) {
                    view.showLoading(false)
                    view.showError(0, "Internal Server Error")
                }

                override fun onResponse(
                    call: Call<ModelResponsePembayaran>,
                    response: Response<ModelResponsePembayaran>
                ) {
                    view.showLoading(false)
                    if (response.code() == 200) {
                        val data = response.body()?.response
                        data?.let{
                            view.showData(it)
                        }
                    } else if (response.code() == 500) {
                        view.showError(500, "Internal Server Error")
                    } else {
                        //http code selain 200
                        response.errorBody()?.run {
                            val diagnostic = Gson().fromJson(
                                this.toString(),
                                ModelResponsePembayaran::class.java
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
        loadData()
    }

}