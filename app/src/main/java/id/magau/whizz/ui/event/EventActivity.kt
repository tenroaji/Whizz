package id.magau.whizz.ui.event

import android.os.Bundle
import android.os.PersistableBundle
import androidx.recyclerview.widget.LinearLayoutManager
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelEvent
import id.magau.whizz.utils.BaseActivity
import id.magau.whizz.utils.toast
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.activity_events.*
import kotlinx.android.synthetic.main.item_loading.*


/**
 * Created by Andi Tenroaji Ahmad on 3/8/2020.
 */

class EventActivity : BaseActivity(R.color.colorWhite,R.layout.activity_events),EventContracts.View {
    private lateinit var mPresenter : EventContracts.Presenter
    private val mAdapter = AdapterEvent()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        mRecyclerEvent.layoutManager = LinearLayoutManager(this)
        mRecyclerEvent.adapter = mAdapter

        val mData2 = arrayListOf(
            ModelEvent("HARD SKILL","The Complete App Design Course - UX, UI and Design Thinking",0,"MAKASSAR","Rp 207.900"),
            ModelEvent("SOFT SKILL","The Complete App Design Course - UX, UI and Design Thinking",0,"MAKASSAR","Rp 207.900"),
            ModelEvent("HARD SKILL","The Complete App Design Course - UX, UI and Design Thinking",0,"MAKASSAR","Rp 207.900"))
        showEvent(mData2)
    }


    override fun showEvent(data: ArrayList<ModelEvent>) {
        mAdapter.updateAdapter(data)
    }

    override fun showLoading(show: Boolean) {
        mProgresBar visibility show
    }

    override fun showError(code: Int?, message: String?) {
        toast("$code -> $message")
    }

    override fun setPresenter(presenter: EventContracts.Presenter) {
        mPresenter = presenter
    }
}