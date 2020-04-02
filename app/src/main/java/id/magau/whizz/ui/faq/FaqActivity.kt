package id.magau.whizz.ui.faq

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelFAQ
import id.magau.whizz.ui.skill_detail.pratinjau.AdapterFAQ
import id.magau.whizz.utils.BaseActivity
import id.magau.whizz.utils.toast
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.activity_faq.*
import kotlinx.android.synthetic.main.item_loading.*

/**
 * Created by Andi Tenroaji Ahmad on 3/18/2020.
 */

class FaqActivity : BaseActivity(layout = R.layout.activity_faq), FaqContracts.View {
    companion object {
        const val KEY_ID_PRODUK = "ID_PRODUK"
    }

    private val idProduct by lazy {
        intent.getStringExtra(KEY_ID_PRODUK)
    }

    private val mAdapter = AdapterFAQ()
    private lateinit var mPresenter: FaqContracts.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FaqPresenter(this, this)
        mPresenter.loadFAQ(idProduct)
        mRecyclerFAQ.layoutManager = LinearLayoutManager(this)
        mRecyclerFAQ.adapter = mAdapter
    }

    override fun showFAQ(data: ArrayList<ModelFAQ?>) {
        mAdapter.updateAdapter(data)
    }

    override fun showLoading(show: Boolean) {
         mProgresBar visibility show
    }

    override fun showError(code: Int, message: String?) {
        toast("$code -> $message")
    }

    override fun setPresenter(presenter: FaqContracts.Presenter) {
        mPresenter = presenter
    }
}