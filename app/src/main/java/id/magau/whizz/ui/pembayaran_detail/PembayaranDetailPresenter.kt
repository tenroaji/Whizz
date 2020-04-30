package id.magau.whizz.ui.pembayaran_detail

import android.content.Context
import com.google.gson.Gson
import id.magau.whizz.R
import id.magau.whizz.data.model.*
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

class PembayaranDetailPresenter(val context: Context, val mView: PembayaranDetailContracts.View) :
    PembayaranDetailContracts.Presenter {
    private val mService: PembayaranApiRoute = RetrofitUtils.createService(
        context.resources.getString(R.string.base_url),
        PembayaranApiRoute::class.java,
        30000L, HttpLoggingInterceptor.Level.HEADERS
    )
    private var mToken = ""
    init {
        mView.setPresenter(this)
        val session = SessionUtils(context)
        mToken = session.getData(PREF_KEY_TOKEN, "")
    }


    override fun loadData(idProduct: String, kodeBank: String, type: String) {
//        val requestBody : RequestBody = MultipartBody.Builder()
//            .setType(MultipartBody.FORM)
//            .addFormDataPart("idcourse", idProduct)
//            .addFormDataPart("bank", kodeBank)
//            .build()
        mView.showLoading(true)
        mService.createCheckout(mToken, idProduct, kodeBank,type).apply {
            enqueue(object : Callback<ModelResponsePembayaranDetail> {
                override fun onFailure(call: Call<ModelResponsePembayaranDetail>, t: Throwable) {
                    mView.showLoading(false)
                    mView.showError(0, "Internal Server Error")
                }

                override fun onResponse(
                    call: Call<ModelResponsePembayaranDetail>,
                    response: Response<ModelResponsePembayaranDetail>
                ) {
                    mView.showLoading(false)
                    if (response.code() in 200..299) {
                        response.body()?.response?.let {
                            mView.showData(it)
                        }?:mView.showError(response.body()?.diagnostic?.code!!,response.body()?.diagnostic?.status)

                    } else if (response.code() == 500) {
//                        mView.showError(500, "Internal Server Error")
                        mView.showCobaLagi(true,"Ada Kesalahan server, Silahkan coba lagi")
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

    override fun sendCheckPayment(idProduct: String) {
        mView.showLoading(true)
        mService.checkPayment(mToken, idProduct).apply {
            enqueue(object : Callback<ModelResponseDiagnostic> {
                override fun onFailure(call: Call<ModelResponseDiagnostic>, t: Throwable) {
                    mView.showLoading(false)
                    mView.showError(0, "Internal Server Error")
                }

                override fun onResponse(
                    call: Call<ModelResponseDiagnostic>,
                    response: Response<ModelResponseDiagnostic>
                ) {
                    mView.showLoading(false)
                    if (response.code() == 200) {
                            mView.showCheckPayment(true)
                    } else if (response.code() == 500) {
//                        mView.showError(500, "Internal Server Error")
                        mView.showToast("Ada Kesalahan server, Silahkan coba lagi")
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

    override fun getCheckOut() {
        mService.getCheckOut(mToken).apply {
            enqueue(object : Callback<ModelResponseCheckOut> {
                override fun onFailure(call: Call<ModelResponseCheckOut>, t: Throwable) {
                    mView.showLoading(false)
                    mView.showError(0, "Internal Server Error")
                }

                override fun onResponse(
                    call: Call<ModelResponseCheckOut>,
                    response: Response<ModelResponseCheckOut>
                ) {
                    mView.showLoading(false)
                    if (response.code() in 200..299) {
                        response.body()?.response?.let {
//                            mView.showData(it)
                        }?:mView.showError(response.body()?.diagnostic?.code!!,response.body()?.diagnostic?.status)

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