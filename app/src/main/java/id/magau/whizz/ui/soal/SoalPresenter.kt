package id.magau.whizz.ui.soal

import android.content.Context
import id.magau.etryout.R
import id.magau.etryout.data.api.SoalApiRoute
import id.magau.etryout.data.model.ModelResponseJawabSoal
import id.magau.etryout.data.model.ModelResponseSoal
import id.magau.etryout.utils.RetrofitUtils
import id.magau.etryout.utils.Session
import id.magau.etryout.utils.Session.Companion.PREF_KEY_TOKEN
import id.magau.etryout.utils.TimeHandler
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

class SoalPresenter(val context: Context, val view: SoalContracts.View) :
    SoalContracts.Presenter {
    private val mService: SoalApiRoute = RetrofitUtils.createService(
        context.resources.getString(R.string.base_url),
        SoalApiRoute::class.java,
        30000L,
        HttpLoggingInterceptor.Level.HEADERS
    )
    private var mToken: String = ""

    init {
        view.setPresenter(this)
        val session = Session.getInstance(context)
        mToken = session.getData(PREF_KEY_TOKEN, "")
    }


    override fun getSoal() {
        view.showLoading(true)
        mService.getSoal(mToken).enqueue(object : Callback<ModelResponseSoal> {
            override fun onFailure(call: Call<ModelResponseSoal>, t: Throwable) {
                view.showLoading(false)
                view.showError(0, "Server Internal Error $t")
            }

            override fun onResponse(
                call: Call<ModelResponseSoal>,
                response: Response<ModelResponseSoal>
            ) {

                if (response.code() == 200) {
                    val history = response.body()?.response?.history
                    if (history != null){
                        view.setUUID(history.uuid?:"")
                    }
                    val mData = response.body()?.response?.historiJawaban
                    if (mData != null) {
                        view.showLoading(false)
                        view.showSoal(mData)
                        val end = response.body()?.response?.history?.end_tryout?:""
                        val start = response.body()?.response?.now_date?:""
                        val duration = TimeHandler.timesUploadHandler(end,start,context)
                        view.showDuration(duration)
                    }
                } else if (response.code() == 422){
                    sendFinishSoal()
                  view.showAnalisis()
                }else {
                    view.showError(0, "Server Internal Error")
                }
            }
        })
    }

    override fun getPembahasan(uuid: String) {
        view.showLoading(true)
        mService.getPembahasan(mToken,uuid).enqueue(object : Callback<ModelResponseSoal> {
            override fun onFailure(call: Call<ModelResponseSoal>, t: Throwable) {
                view.showLoading(false)
                view.showError(0, "Server Internal Error $t")
            }

            override fun onResponse(
                call: Call<ModelResponseSoal>,
                response: Response<ModelResponseSoal>
            ) {
                view.showLoading(false)
                if (response.code() == 200) {
                    val mData = response.body()?.response?.historiJawaban
                    if (!mData.isNullOrEmpty()) {
                        view.showSoal(mData)
//                        val end = response.body()?.response?.history?.end_tryout?:""
//                        val start = response.body()?.response?.now_date?:""
//                        val duration = TimeHandler.timesUploadHandler(end,start,context)
//                        view.showDuration(duration)
                    }else{
                        view.showError(0, "Server Internal Error")
                    }
                } else {
                    view.showError(0, "Server Internal Error")
                }
            }
        })
    }

    override fun sendFinishSoal() {
        mService.sendFinishSoal(mToken).enqueue(object : Callback<ModelResponseSoal> {
            override fun onFailure(call: Call<ModelResponseSoal>, t: Throwable) {
                view.showError(0, "Server Internal Error $t")
            }

            override fun onResponse(
                call: Call<ModelResponseSoal>,
                response: Response<ModelResponseSoal>
            ) {
                view.showLoading(false)
                if (response.code() == 200) {
//                    val mData = response.body()?.response?.historiJawaban
//                    if (mData != null) {
//                        view.showSoal(mData)
//                    }
                    getSoal()
                }
            }
        })
    }

    override fun kumpulSoal() {
        view.showLoading(true)
        mService.sendFinishSoal(mToken).enqueue(object : Callback<ModelResponseSoal> {
            override fun onFailure(call: Call<ModelResponseSoal>, t: Throwable) {
                view.showLoading(false)
                view.showError(0, "Server Internal Error $t")
            }

            override fun onResponse(
                call: Call<ModelResponseSoal>,
                response: Response<ModelResponseSoal>
            ) {
                view.showLoading(false)
                if (response.code() == 200) {
//                    val mData = response.body()?.response?.historiJawaban
//                    if (mData != null) {
//                        view.showSoal(mData)
//                    }
                    view.showAnalisis()
                }else {
                    view.showError(0, "Server Internal Error")
                }
            }
        })
    }

    override fun sendTWK(idSoal: String, pilihan: String, idHistory: String) {
        view.showLoading(true)
        mService.sendTWK(mToken,idSoal,pilihan,idHistory).enqueue(object : Callback<ModelResponseJawabSoal> {
            override fun onFailure(call: Call<ModelResponseJawabSoal>, t: Throwable) {
                view.showLoading(false)
                view.showError(0, "Server Internal Error $t")
            }

            override fun onResponse(
                call: Call<ModelResponseJawabSoal>,
                response: Response<ModelResponseJawabSoal>
            ) {
                view.showLoading(false)
                if (response.code() == 200) {

                }else if (response.code() == 500){
                    view.showError(500,"Server Internal error")
                }else {
                    view.showError(0,"Server Internal error")
                }
            }
        })
    }

    override fun sendTIU(idSoal: String, pilihan: String, idHistory: String) {
        view.showLoading(true)
        mService.sendTIU(mToken,idSoal,pilihan,idHistory).enqueue(object : Callback<ModelResponseJawabSoal> {
            override fun onFailure(call: Call<ModelResponseJawabSoal>, t: Throwable) {
                view.showLoading(false)
                view.showError(0, "Server Internal Error $t")
            }

            override fun onResponse(
                call: Call<ModelResponseJawabSoal>,
                response: Response<ModelResponseJawabSoal>
            ) {
                view.showLoading(false)
                if (response.code() == 200) {

                }else if (response.code() == 500){
                    view.showError(500,"Server Internal error")
                }else {
                    view.showError(0,"Server Internal error")
                }
            }
        })
    }

    override fun sendTKP(idSoal: String, pilihan: String, idHistory: String) {
        view.showLoading(true)
        mService.sendTKP(mToken,idSoal,pilihan,idHistory).enqueue(object : Callback<ModelResponseJawabSoal> {
            override fun onFailure(call: Call<ModelResponseJawabSoal>, t: Throwable) {
                view.showLoading(false)
                view.showError(0, "Server Internal Error $t")
            }

            override fun onResponse(
                call: Call<ModelResponseJawabSoal>,
                response: Response<ModelResponseJawabSoal>
            ) {
                view.showLoading(false)
                if (response.code() == 200) {

                }else if (response.code() == 500){
                    view.showError(500,"Server Internal error")
                }else {
                    view.showError(0,"Server Internal error")
                }
            }
        })
    }

    override fun forceUpdate() {

    }

    override fun start() {
        getSoal()
    }

}

