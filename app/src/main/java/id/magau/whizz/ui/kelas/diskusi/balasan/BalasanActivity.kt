package id.magau.whizz.ui.kelas.diskusi.balasan

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
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
    const val KEY_KELAS_SAYA = "KELAS_SAYA"
}

    private var mAdapter = AdapterBalasan()
    private val idComments by lazy {
        intent.getStringExtra(KEY_ID_COMMENT)
    }

    private val kelasSaya by lazy {
        intent.getBooleanExtra(KEY_ID_COMMENT,false)
    }
    private lateinit var mPresenter : BalasanContracts.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BalasanPresenter(this,this)
        mPresenter.loadData(idComments)

        editBalasan visibility kelasSaya

        editBalasan.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    mPresenter.sendReplys(editBalasan.text.toString(),idComments)
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


    fun showInput(status: Boolean) {
        val input = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (status) {
            editBalasan.requestFocus()
            input.showSoftInput(editBalasan, InputMethodManager.SHOW_IMPLICIT)
        } else
            input.hideSoftInputFromWindow(editBalasan.windowToken, 0)
    }

    override fun sendDone() {
        editBalasan.text = null
        showInput(false)
        mPresenter.loadData(idComments)
        mRecyclerReplys.smoothScrollToPosition(mAdapter.itemCount - 1)
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