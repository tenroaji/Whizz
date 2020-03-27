package id.magau.whizz.ui.main_menu

import android.content.Context
import com.google.gson.Gson
import id.magau.whizz.R
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

class MainMenuPresenter(val context: Context, val view: MainMenuContracts.View) :
    MainMenuContracts.Presenter {
    private val mService: MainMenuApiRoute = RetrofitUtils.createService(
        context.resources.getString(R.string.base_url),
        MainMenuApiRoute::class.java,
        30000L, HttpLoggingInterceptor.Level.HEADERS
    )
    private var mToken =""
    init {
        view.setPresenter(this)
        val session = SessionUtils(context)
        mToken = session.getData(PREF_KEY_TOKEN, "")
    }

    override fun loadData() {
        view.showLoading(true)
        mService.mainMenu(mToken).apply {
            enqueue(object : Callback<ModelResponseMainMenu> {
                override fun onFailure(call: Call<ModelResponseMainMenu>, t: Throwable) {
                    view.showLoading(false)
                    view.showError(0, "Internal Server Error")
                }

                override fun onResponse(
                    call: Call<ModelResponseMainMenu>,
                    response: Response<ModelResponseMainMenu>
                ) {
                    view.showLoading(false)
                    if (response.code() == 200) {
                        val data = response.body()?.response
                        view.showCount(data?.course_count)
                        data?.events?.let{
                            view.showEvents(it)
                        }
                        data?.products_promo?.let{
                            view.showPromo(it)
                        }
                        data?.products?.let{
                            view.showSkillPopular(it)
                        }
                    } else if (response.code() == 500) {
                        view.showError(500, "Internal Server Error")
                    } else {
                        //http code selain 200
                        response.errorBody()?.run {
                            val diagnostic = Gson().fromJson(
                                this.toString(),
                                ModelResponseMainMenu::class.java
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