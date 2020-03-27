package id.magau.whizz.ui.daftar

import android.content.Context
import com.google.gson.Gson
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelResponseRegister
import id.magau.whizz.data.services.RegisterApiRoute
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

class DaftarPresenter(val context: Context, val mView: DaftarContracts.View) :
    DaftarContracts.Presenter {
    private val mService = RetrofitUtils.createService(
        context.resources.getString(R.string.base_url),
        RegisterApiRoute::class.java,
        30000L
        , HttpLoggingInterceptor.Level.HEADERS
    )

    init {
        mView.setPresenter(this)
    }

    override fun sendRegister(
        nama: String,
        user: String,
        ktp: String,
        email: String,
        hp: String,
        jk: String,
        alamat: String,
        password: String
    ) {
        mView.showLoading(true)
        mService.register(nama, user, ktp, email, hp, jk, alamat, password).apply {
            enqueue(object : Callback<ModelResponseRegister> {
                override fun onFailure(call: Call<ModelResponseRegister>, t: Throwable) {
                    mView.showLoading(false)
                    mView.showError(0, "Internal Server Error")
                }

                override fun onResponse(
                    call: Call<ModelResponseRegister>,
                    response: Response<ModelResponseRegister>
                ) {
                    mView.showLoading(false)
                    when (response.code()){
                         200 -> {
                             val data = response.body()!!
                             val mSession = SessionUtils(context)
                             val mToken = data.response?.access_token
                             val mType = data.response?.token_type
                             if (mToken != null) {
                                 mSession.editData(PREF_KEY_TOKEN, "$mType $mToken")
                             }
                             mSession.editData(SessionUtils.PREF_KEY_LOGIN, true)
                             data.response?.user?.name?.let { mSession.editData(SessionUtils.PREF_KEY_NAME, it) }
                             data.response?.user?.email?.let { mSession.editData(SessionUtils.PREF_KEY_EMAIL, it) }
                            mView.showRegis()
                        }
                         201 -> {
                             val data = response.body()?.diagnostic
                             mView.showError(data?.code,data?.status)
                        }

                         500 -> {
                            mView.showError(500, "Internal Server Error")
                        }
                        else -> {
                            response.errorBody()?.run {
                                val diagnostic = Gson().fromJson(
                                    this.string(),
                                    ModelResponseRegister::class.java
                                )
                                mView.showError(
                                    diagnostic.diagnostic?.code!!,
                                    diagnostic.diagnostic?.status
                                )
                            }
                        }
                    }
                }

            })
        }
    }

    override fun start() {

    }

}