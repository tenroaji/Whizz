package id.magau.whizz.ui.kelas.materi.raitng

import android.content.Context
import com.google.gson.Gson
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelResponseDiagnostic
import id.magau.whizz.data.services.SkillsApiRoute
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

class RatingPresenter(val context: Context, val mView: RatingContracts.View) :
    RatingContracts.Presenter {
    private val mService: SkillsApiRoute = RetrofitUtils.createService(
        context.resources.getString(R.string.base_url),
        SkillsApiRoute::class.java,
        30000L, HttpLoggingInterceptor.Level.HEADERS
    )
    private var mToken = ""
    init {
        mView.setPresenter(this)
        val session = SessionUtils(context)
        mToken = session.getData(PREF_KEY_TOKEN, "")
    }


    override fun loadData(idProduct: String, rating: Int) {
        mView.showLoading(true)
        mService.sendRating(mToken,idProduct,rating).apply {
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
                        mView.showToast("Berhasil Mengirimkan Rating")
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