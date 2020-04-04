package id.magau.whizz.ui.kelas.diskusi.balasan

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelReplys
import id.magau.whizz.utils.BaseActivity
import id.magau.whizz.utils.toast
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.activity_balasan.*
import kotlinx.android.synthetic.main.item_loading.*


/**
 * Created by siapaSAYA on 4/3/2020
 */

class BalasanActivity :BaseActivity(layout = R.layout.activity_balasan),BalasanContracts.View{
companion object{
    const val KEY_ID_COMMENT = "ID_COMMENT"
}

    private var mAdapter = AdapterBalasan()
    private val idComments by lazy {
        intent.getStringExtra(KEY_ID_COMMENT)
    }
    private lateinit var mPresenter : BalasanContracts.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BalasanPresenter(this,this)
        mPresenter.loadData(idComments)

        editBalasan.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    mPresenter.sendReplys(idComments,editBalasan.text.toString())
                    true
                }
                else -> false
            }
        }
        mRecyclerReplys.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    override fun showData(data: ArrayList<ModelReplys?>) {
        mAdapter.updateAdapter(data)
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

    override fun setPresenter(presenter: BalasanContracts.Presenter) {
        mPresenter = presenter
    }
}