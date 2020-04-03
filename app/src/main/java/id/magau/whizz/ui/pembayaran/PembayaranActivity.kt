package id.magau.whizz.ui.pembayaran

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelPembayaran
import id.magau.whizz.utils.BaseActivity
import id.magau.whizz.utils.toast
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.activity_pembayaran.*
import kotlinx.android.synthetic.main.item_loading.*

/**
 * Created by Andi Tenroaji Ahmad on 3/19/2020.
 */

class PembayaranActivity : BaseActivity(layout = R.layout.activity_pembayaran),PembayaranContracts.View {
    companion object{
        const val KEY_ID_PRODUCT = "ID_PRODUCT"
    }

    private val idProduct by lazy {
        intent.getStringExtra(KEY_ID_PRODUCT)
    }


    private lateinit var mPresenter : PembayaranContracts.Presenter
    private val mAdapter = AdapterPembayaran()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PembayaranPresenter(this,this)
        mPresenter.start()
        mRecyclerPembayaran.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
        mAdapter.updateIdProduct(idProduct)
    }

    override fun showData(data: ArrayList<ModelPembayaran?>) {
        mAdapter.updateAdapter(data)

    }

    override fun showLoading(show: Boolean) {
        mProgresBar visibility show
    }

    override fun showError(code: Int, message: String?) {
        toast("$code -> $message")
    }

    override fun setPresenter(presenter: PembayaranContracts.Presenter) {
        mPresenter = presenter
    }
}