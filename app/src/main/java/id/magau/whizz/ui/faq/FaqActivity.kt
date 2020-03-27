package id.magau.whizz.ui.faq

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelFAQ
import id.magau.whizz.utils.BaseActivity
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.activity_faq.*
import kotlinx.android.synthetic.main.item_loading.*

/**
 * Created by Andi Tenroaji Ahmad on 3/18/2020.
 */

class FaqActivity : BaseActivity(layout = R.layout.activity_faq),FaqContracts.View {
    private val mAdapter = AdapterFAQ()
    private lateinit var mPresenter : FaqContracts.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mRecyclerFAQ.layoutManager = LinearLayoutManager(this)
        mRecyclerFAQ.adapter = mAdapter
    }

    override fun showFAQ(data: ArrayList<ModelFAQ?>) {

    }

    override fun showLoading(show: Boolean) {
        mProgresBar visibility show
    }

    override fun showError(code: Int, message: String?) {
        TODO("Not yet implemented")
    }

    override fun setPresenter(presenter: FaqContracts.Presenter) {
        TODO("Not yet implemented")
    }
}