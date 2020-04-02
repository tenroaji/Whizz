package id.magau.whizz.ui.pembayaran_detail

import android.content.Context
import com.google.gson.Gson
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelResponsePembayaranDetail
import id.magau.whizz.data.model.reqBodyPembayaran
import id.magau.whizz.data.services.PembayaranApiRoute
import id.magau.whizz.utils.RetrofitUtils
import id.magau.whizz.utils.SessionUtils
import id.magau.whizz.utils.SessionUtils.Companion.PREF_KEY_TOKEN
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

class PembayaranDetailPresenter(val context: Context, val view: PembayaranDetailContracts.View) :
    PembayaranDetailContracts.Presenter {
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


    override fun loadData(idProduct: String, kodeBank: String) {
        view.showLoading(true)
//        val requestBody : RequestBody = MultipartBody.Builder()
//            .setType(MultipartBody.FORM)
//            .addFormDataPart("idcourse", idProduct)
//            .addFormDataPart("bank", kodeBank)
//            .build()

        mService.createCheckout(mToken, reqBodyPembayaran(idProduct, kodeBank)).apply {
            enqueue(object : Callback<ModelResponsePembayaranDetail> {
                override fun onFailure(call: Call<ModelResponsePembayaranDetail>, t: Throwable) {
                    view.showLoading(false)
                    view.showError(0, "Internal Server Error")
                }

                override fun onResponse(
                    call: Call<ModelResponsePembayaranDetail>,
                    response: Response<ModelResponsePembayaranDetail>
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
                                ModelResponsePembayaranDetail::class.java
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