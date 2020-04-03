package id.magau.whizz.ui.main_menu

import android.content.Context
import com.google.gson.Gson
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelDiagnostic
import id.magau.whizz.data.model.ModelResponseMainMenu
import id.magau.whizz.data.services.LoginApiRoute
import id.magau.whizz.data.services.MainMenuApiRoute
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

class MainMenuPresenter(val context: Context, val mView: MainMenuContracts.View) :
    MainMenuContracts.Presenter {
    private val mService: MainMenuApiRoute = RetrofitUtils.createService(
        context.resources.getString(R.string.base_url),
        MainMenuApiRoute::class.java,
        30000L, HttpLoggingInterceptor.Level.HEADERS
    )
    private var mToken =""
    init {
        mView.setPresenter(this)
        val session = SessionUtils(context)
        mToken = session.getData(PREF_KEY_TOKEN, "")
    }

    override fun loadData() {
        mView.showLoading(true)
        mService.mainMenu(mToken).apply {
            enqueue(object : Callback<ModelResponseMainMenu> {
                override fun onFailure(call: Call<ModelResponseMainMenu>, t: Throwable) {
                    mView.showLoading(false)
                    mView.showError(0, "Internal Server Error")
                }

                override fun onResponse(
                    call: Call<ModelResponseMainMenu>,
                    response: Response<ModelResponseMainMenu>
                ) {
                    mView.showLoading(false)
                    if (response.code() == 200) {
                        val data = response.body()?.response
                        mView.showCount(data?.course_count)
                        data?.events?.let{
                            mView.showEvents(it)
                        }
                        data?.products_promo?.let{
                            mView.showPromo(it)
                        }
                        data?.products?.let{
                            mView.showSkillPopular(it)
                        }
                    } else if (response.code() == 500) {
                        mView.showError(500, "Internal Server Error")
                    } else {
                        //http code selain 200
                        response.errorBody()?.run {
                            response.errorBody()?.string()?.run {
                                val diagnostic = Gson().fromJson(
                                    this,
                                    ModelDiagnostic::class.java
                                )
                                mView.showError(
                                    diagnostic.code,
                                    diagnostic.status
                                )
                            }
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