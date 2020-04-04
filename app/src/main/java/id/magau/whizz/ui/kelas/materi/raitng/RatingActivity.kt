package id.magau.whizz.ui.kelas.materi.raitng

import android.os.Bundle
import id.magau.whizz.R
import id.magau.whizz.utils.*
import kotlinx.android.synthetic.main.activity_rating.*
import kotlinx.android.synthetic.main.item_loading.*

class RatingActivity : BaseActivity(layout = R.layout.activity_rating),RatingContracts.View{
    companion object{
        const val KEY_ID_PRODUCT = "ID_PRODUCT"
    }

    private val idProduct by lazy {
        intent.getStringExtra(KEY_ID_PRODUCT)
    }




    private lateinit var mPresenter: RatingContracts.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        RatingPresenter(this,this)

        val session = SessionUtils(this)
        val nama = session.getData(SessionUtils.PREF_KEY_NAME, "")
        val initialName = getInitialName(nama.toUpperCase())
        val iconSize = resources.getDimensionPixelSize(R.dimen.margin_28dp)
        val mColor = ColorGenerator.APP.getColor(nama.length)
        val icon = TextDrawable.builder(this).buildRound(initialName, mColor, iconSize, iconSize)
        imgUser.setImageDrawable(icon)

        groupTestimoni visibility false

        btnPostTestimoni.setOnClickListener {
            mPresenter.loadData(idProduct,ratingBar.rating.toInt())
        }

    }

    override fun showToast(data: String) {
        toast(data)
    }


    override fun showLoading(show: Boolean) {
        mProgresBar visibility show
    }

    override fun showError(code: Int, message: String?) {
        toast("$code -> $message")
    }

    override fun setPresenter(presenter: RatingContracts.Presenter) {
        mPresenter = presenter
    }
}