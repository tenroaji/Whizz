package id.magau.whizz.ui.event

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelEvent
import id.magau.whizz.data.model.ModelEvents
import id.magau.whizz.ui.event_search.EventSearchActivity
import id.magau.whizz.utils.BaseActivity
import id.magau.whizz.utils.ripple
import id.magau.whizz.utils.toast
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.activity_events.*
import kotlinx.android.synthetic.main.item_loading.*


/**
 * Created by Andi Tenroaji Ahmad on 3/8/2020.
 */

class EventActivity : BaseActivity(R.color.colorWhite, R.layout.activity_events),
    EventContracts.View {
    companion object {
        const val KEY_SKILL_SAYA = "SKILL_SAYA"
    }

    private val myEvents by lazy {
        intent.getBooleanExtra(KEY_SKILL_SAYA,false)
    }


    private lateinit var mPresenter: EventContracts.Presenter
    private val mAdapter = AdapterEvents()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventPresenter(this,this)
        if(myEvents){
            mAdapter.updateClass(true)
            mPresenter.loadMyEvent()
        }else{
            mPresenter.start()
        }



        mToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        val mLayoutManager = LinearLayoutManager(this)
        mRecyclerEvent.layoutManager = mLayoutManager
        mRecyclerEvent.adapter = mAdapter

        mRecyclerEvent.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemPosition = mLayoutManager.findLastVisibleItemPosition()
//                Log.e(
//                    "onscroll",
//                    "item = ${mLayoutManager.itemCount} visible = ${visibleItemPosition} total =${mLayoutManager.itemCount - visibleItemPosition}, next = ${mPresenter.isCanNextPage()}"
//                )
                if ((mLayoutManager.itemCount - visibleItemPosition) <= 1 && mPresenter.isCanNextPage()) {
                        mPresenter.loadEvent()
                }
            }
        })


        imgSearch.ripple().setOnClickListener {
            startActivity(Intent(this,EventSearchActivity::class.java))
        }

    }


    override fun showEvent(data: ArrayList<ModelEvents?>) {
        mAdapter.updateAdapter(data)
    }

    override fun showLoading(show: Boolean) {
        mProgresBar visibility show
    }

    override fun showNextLoading(show: Boolean) {
        mAdapter.setLoading(show)
    }

    override fun showError(code: Int?, message: String?) {
        toast("$code -> $message")
    }

    override fun showNoData() {

    }

    override fun setPresenter(presenter: EventContracts.Presenter) {
        mPresenter = presenter
    }
}