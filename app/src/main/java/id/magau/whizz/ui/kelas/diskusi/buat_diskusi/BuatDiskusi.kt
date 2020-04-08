package id.magau.whizz.ui.kelas.diskusi.buat_diskusi

import android.os.Bundle
import id.magau.whizz.R
import id.magau.whizz.utils.*
import kotlinx.android.synthetic.main.activity_buat_disukusi.*
import kotlinx.android.synthetic.main.item_loading.*


/**
 * Created by siapaSAYA on 4/3/2020
 */

class BuatDiskusi : BaseActivity(layout = R.layout.activity_buat_disukusi),BuatDiskusiContracts.View {

    companion object {
        const val KEY_ID_PRODUK = "ID_PRODUK"
    }

    val idProduct by lazy {
        intent.getStringExtra(KEY_ID_PRODUK)!!
    }

    private lateinit var mPresenter : BuatDiskusiContracts.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        BuatDiskusiPresenter(this, this)

        btnPostDiskusi.setOnClickListener {
            mPresenter.sendData(idProduct,editComment.text.toString())
        }

        val session = SessionUtils(this)
        val nama = session.getData(SessionUtils.PREF_KEY_NAME, "")
        val initialName = getInitialName(nama.toUpperCase())
        val iconSize = resources.getDimensionPixelSize(R.dimen.margin_28dp)
        val mColor = ColorGenerator.APP.getColor(nama.length)
        val icon = TextDrawable.builder(this).buildRound(initialName, mColor, iconSize, iconSize)
        imgUser.setImageDrawable(icon)

    }

    override fun doneComment() {
        editComment.text = null
    }

    override fun showLoading(show: Boolean) {
        mProgresBar visibility show
    }

    override fun showToast(data: String) {
        toast(data)
    }

    override fun showError(code: Int, message: String?) {
        toast("$code -> $message")
    }

    override fun setPresenter(presenter: BuatDiskusiContracts.Presenter) {
        mPresenter = presenter
    }
}