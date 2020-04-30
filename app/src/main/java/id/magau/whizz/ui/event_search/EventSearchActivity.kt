package id.magau.whizz.ui.event_search

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelEvents
import id.magau.whizz.ui.event.AdapterEvents
import id.magau.whizz.utils.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_skill.imgFilter
import kotlinx.android.synthetic.main.item_loading.*

/**
 * Created by Andi Tenroaji Ahmad on 3/8/2020.
 */

class EventSearchActivity : BaseActivity(R.color.colorWhite,R.layout.activity_search),EventSearchContracts.View {
    companion object {
        const val KEY_SKILL_SAYA = "SKILL_SAYA"
    }

    private val mySkill by lazy {
        intent.getBooleanExtra(KEY_SKILL_SAYA,false)
    }
    private lateinit var mPresenter : EventSearchContracts.Presenter
    private val mAdapterSkills = AdapterEvents()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imgFilter visibility false
        EventSearchPresenter(this,this)

        btnBatal.setOnClickListener {
            finish()
        }

        btnTelusuri.setOnClickListener {
            mPresenter.searchEvent(editSearch.text.toString())
        }


        val mLayoutManager = LinearLayoutManager(this)
        mRecyclerSearch.layoutManager = mLayoutManager
        mRecyclerSearch.adapter = mAdapterSkills
        mRecyclerSearch.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemPosition = mLayoutManager.findLastVisibleItemPosition()
                if ((mLayoutManager.itemCount - visibleItemPosition) <= 1 && mPresenter.isCanNextPage()) {
                    mPresenter.searchEvent(editSearch.text.toString())
                }
            }
        })

    }

    override fun showData(data: ArrayList<ModelEvents?>) {
        mAdapterSkills.updateAdapter(data)
    }

    override fun showNextLoading(show: Boolean) {
        mAdapterSkills.setLoading(show)
    }

    override fun showLoading(show: Boolean) {
        mProgresBar visibility show
    }

    override fun showError(code: Int?, message: String?) {
        toast("$code -> $message")
    }

    override fun showNoData() {

    }

    override fun setPresenter(presenter: EventSearchContracts.Presenter) {
        mPresenter = presenter
    }
}