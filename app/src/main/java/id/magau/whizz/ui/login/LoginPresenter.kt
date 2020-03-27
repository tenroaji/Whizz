package id.magau.whizz.ui.login

import android.content.Context
import com.google.gson.Gson
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelResponseLogin
import id.magau.whizz.data.services.LoginApiRoute
import id.magau.whizz.utils.RetrofitUtils
import id.magau.whizz.utils.SessionUtils
import id.magau.whizz.utils.SessionUtils.Companion.PREF_KEY_EMAIL
import id.magau.whizz.utils.SessionUtils.Companion.PREF_KEY_LOGIN
import id.magau.whizz.utils.SessionUtils.Companion.PREF_KEY_NAME
import id.magau.whizz.utils.SessionUtils.Companion.PREF_KEY_TOKEN
import okhttp3.MultipartBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

class LoginPresenter(val context: Context, val view: LoginContracts.View) :
    LoginContracts.Presenter {
    private val mService: LoginApiRoute = RetrofitUtils.createService(
        context.resources.getString(R.string.base_url),
        LoginApiRoute::class.java,
        30000L, HttpLoggingInterceptor.Level.HEADERS
    )

    init {
        view.setPresenter(this)
    }

    override fun sendLogin(username: String, password: String) {
        view.showLoading(true)
        mService.loginUser(username, password).apply {
            enqueue(object : Callback<ModelResponseLogin> {
                override fun onFailure(call: Call<ModelResponseLogin>, t: Throwable) {
                    view.showLoading(false)
                    view.showError(0, "Internal Server Error")
                }

                override fun onResponse(
                    call: Call<ModelResponseLogin>,
                    response: Response<ModelResponseLogin>
                ) {
                    view.showLoading(false)
                    if (response.code() == 200) {
                        val data = response.body()!!
                        val mSession = SessionUtils(context)
                        val mToken = data.response?.access_token
                        val mType = data.response?.token_type
                        if (mToken != null) {
                            mSession.editData(PREF_KEY_TOKEN, "$mType $mToken")
                        }
                        mSession.editData(PREF_KEY_LOGIN, true)
                        data.response?.user?.name?.let { mSession.editData(PREF_KEY_NAME, it) }
                        data.response?.user?.email?.let { mSession.editData(PREF_KEY_EMAIL, it) }
                        view.openMain()
                    } else if (response.code() == 500) {
                        view.showError(500, "Internal Server Error")
                    } else {
                        //http code selain 200
                        response.errorBody()?.run {
                            val diagnostic = Gson().fromJson(
                                this.toString(),
                                ModelResponseLogin::class.java
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