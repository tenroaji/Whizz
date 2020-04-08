package id.magau.whizz.ui.pembayaran_detail

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.widget.Toast
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelPembayaranDetail
import id.magau.whizz.ui.products.ProductsActivity
import id.magau.whizz.utils.*
import kotlinx.android.synthetic.main.activity_detail_pembayaran.*
import kotlinx.android.synthetic.main.item_loading.*
import kotlinx.android.synthetic.main.item_try_again.*

/**
 * Created by Andi Tenroaji Ahmad on 3/19/2020.
 */

class PembayaranDetail : BaseActivity(layout = R.layout.activity_detail_pembayaran), PembayaranDetailContracts.View{
    companion object{
        const val KEY_ID_PRODUCT = "ID_PRODUCT"
        const val KEY_KODE_BANK = "KODE_BANK"
        const val KEY_TITLE_BANK = "TITLE_BANK"
        const val KEY_IMAGE_BANK = "IMAGE_BANK"
    }

    private val idProduct by lazy {
        intent.getStringExtra(KEY_ID_PRODUCT)
    }

    private val kodeBank by lazy {
        intent.getStringExtra(KEY_KODE_BANK)
    }

    private val titleBank by lazy {
        intent.getStringExtra(KEY_TITLE_BANK)
    }

    private val imgBank by lazy {
        intent.getStringExtra(KEY_IMAGE_BANK)
    }

    private lateinit var mPresenter : PembayaranDetailContracts.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PembayaranDetailPresenter(this,this)

        mPresenter.loadData(idProduct, kodeBank)

        tvNamaBank.text = titleBank
//        imgLogo load imgBank
        val myClipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?
        imgCopy.ripple().setOnClickListener {
                val myClip = ClipData.newPlainText("text", tvNoAkun.text);
                myClipboard?.setPrimaryClip(myClip);
                Toast.makeText(this, "No. Akun Tersalin", Toast.LENGTH_SHORT).show();
        }

        mSwipeRefresh.setOnRefreshListener {
            mPresenter.loadData(idProduct, kodeBank)
        }

        btnCheckPembayaran.setOnClickListener {
            mPresenter.sendCheckPayment(idProduct)
        }

        viewCobaLagi.setOnClickListener {
            mPresenter.loadData(idProduct, kodeBank)
            showCobaLagi(false,null)
        }
    }

    override fun showData(data: ModelPembayaranDetail) {
        tvNoAkun.text = data.no_va
    }

    override fun showCheckPayment(sukses: Boolean) {
        start(ProductsActivity::class.java)
    }

    override fun showCobaLagi(show: Boolean,data : String?) {
        viewCobaLagi visibility show
        data?.let{
            tvCobaLagi.text = data
        }
    }

    override fun showLoading(show: Boolean) {
        if (mSwipeRefresh.isRefreshing) mSwipeRefresh.isRefreshing = false
        mProgresBar visibility show
    }

    override fun showError(code: Int, message: String?) {
        toast("$code -> $message")
    }

    override fun showToast(data: String) {
        toast(data)
    }

    override fun setPresenter(presenter: PembayaranDetailContracts.Presenter) {
        mPresenter = presenter
    }
}