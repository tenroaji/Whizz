package id.magau.whizz.ui.kelas.diskusi.balasan

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelReplys
import id.magau.whizz.utils.*
import kotlinx.android.synthetic.main.activity_balasan.*
import kotlinx.android.synthetic.main.item_list_file.view.*
import kotlinx.android.synthetic.main.item_loading.*


/**
 * Created by siapaSAYA on 4/3/2020
 */

class BalasanActivity :BaseActivity(layout = R.layout.activity_balasan),BalasanContracts.View{
companion object{
    const val KEY_ID_COMMENT = "ID_COMMENT"
    const val KEY_KELAS_SAYA = "KELAS_SAYA"
    const val KEY_NAMA = "KEY_NAMA"
    const val KEY_COMMENT = "COMMENT"
}

    private var mAdapter = AdapterBalasan()
    private val idComments by lazy {
        intent.getStringExtra(KEY_ID_COMMENT)
    }

    private val mNama by lazy {
        intent.getStringExtra(KEY_NAMA)
    }


    private val mComment by lazy {
        intent.getStringExtra(KEY_COMMENT)
    }


    private val kelasSaya by lazy {
        intent.getBooleanExtra(KEY_KELAS_SAYA,false)
    }
    private lateinit var mPresenter : BalasanContracts.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BalasanPresenter(this,this)
        mPresenter.loadData(idComments)
        tvTanggal visibility false
        tvComment.text = mComment
        tvUser.text = mNama

        val initialName = getInitialName(mNama.toUpperCase())
        val iconSize = resources.getDimensionPixelSize(R.dimen.margin_28dp)
        val mColor = ColorGenerator.APP.getColor(mNama.length)
        val icon = TextDrawable.builder(this).buildRound(initialName, mColor, iconSize, iconSize)
        imgComment.setImageDrawable(icon)
        Log.d("lapar",kelasSaya.toString())
        editBalasan visibility kelasSaya

        val session = SessionUtils(this)
        val nama2 = session.getData(SessionUtils.PREF_KEY_NAME, "")
        val initialName2 = getInitialName(nama2.toUpperCase())
        val iconSize2 = resources.getDimensionPixelSize(R.dimen.margin_28dp)
        val mColor2 = ColorGenerator.APP.getColor(nama2.length)
        val icon2 = TextDrawable.builder(this).buildRound(initialName2, mColor2, iconSize2, iconSize2)
        imgUser.setImageDrawable(icon2)

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