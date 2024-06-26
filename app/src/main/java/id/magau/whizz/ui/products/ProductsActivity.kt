package id.magau.whizz.ui.products

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelEventSaya
import id.magau.whizz.data.model.ModelEvents
import id.magau.whizz.data.model.ModelProducts
import id.magau.whizz.ui.event.AdapterEvents
import id.magau.whizz.ui.event_saya.EventSayaActivity
import id.magau.whizz.ui.main_menu.AdapterSkills
import id.magau.whizz.ui.skill.SkillActivity
import id.magau.whizz.utils.*
import id.magau.whizz.utils.SessionUtils.Companion.PREF_KEY_PEMATERI
import kotlinx.android.synthetic.main.activty_product.*
import kotlinx.android.synthetic.main.item_loading.*

/**
 * Created by Andi Tenroaji Ahmad on 3/10/2020.
 */

class ProductsActivity : BaseActivity(R.color.colorWhite, R.layout.activty_product),
    ProductsContracts.View {
    private lateinit var mPresenter : ProductsContracts.Presenter
    private val mAdapterEvent = AdapterEvents()
    private val mAdapterSkills = AdapterSkills()
    private val mAdapterKelas = AdapterSkills()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAdapterEvent.updateClass(true)
        ProductsPresenter(this,this)
        mPresenter.start()
        val mSession = SessionUtils(this)
        if (mSession.getData(PREF_KEY_PEMATERI, false)) {
            mPresenter.loadKelasSaya()
        } else {
            groupKelas visibility false
        }
        mRecyclerEventSaya.layoutManager = LinearLayoutManager(this)
        mRecyclerEventSaya.adapter = mAdapterEvent

        mRecyclerKelasSaya.layoutManager = LinearLayoutManager(this)
        mRecyclerKelasSaya.adapter = mAdapterKelas

        mRecyclerSkillSaya.layoutManager = LinearLayoutManager(this)
        mRecyclerSkillSaya.adapter = mAdapterSkills

        tvLabelSemuaSkill.ripple().setOnClickListener {
            startActivity(Intent(this@ProductsActivity, SkillActivity::class.java).apply {
                putExtra(SkillActivity.KEY_SKILL_SAYA,true)
            })
            overridePendingTransition(R.anim.enter, R.anim.exit)
        }
        tvLabelSemuaKelas.ripple().setOnClickListener {
            startActivity(Intent(this@ProductsActivity, SkillActivity::class.java).apply {
                putExtra(SkillActivity.KEY_SKILL_SAYA,false)
            })
            overridePendingTransition(R.anim.enter, R.anim.exit)
        }
        tvLabelSemuaEvent.ripple().setOnClickListener {
            start(EventSayaActivity::class.java)
        }

        mSwipeRefresh.setOnRefreshListener {
            mPresenter.start()
            if (mSession.getData(PREF_KEY_PEMATERI, false)) {
                mPresenter.loadKelasSaya()
            }
        }

    }

    override fun showSkillSaya(data: ArrayList<ModelProducts?>) {
        if (data.isEmpty()){
            groupSkill visibility false
            return
        }
        if (data.size > 3) {
            val mData = arrayListOf<ModelProducts?>()
            mData.addAll(data.slice(0..2))
            mAdapterSkills.updateAdapter(mData)
        }else {
            mAdapterSkills.updateAdapter(data)
        }

    }

    override fun showKelasSaya(data: ArrayList<ModelProducts?>) {
        if (data.isEmpty()){
            groupKelas visibility false
            return
        }

        if (data.size > 3) {
            val mData = arrayListOf<ModelProducts?>()
            mData.addAll(data.slice(0..2))
            mAdapterKelas.updateAdapter(mData)
        }else {
            mAdapterKelas.updateAdapter(data)
        }

    }

    override fun showEvent(data: ArrayList<ModelEvents?>) {

        if (data.isEmpty()){
            groupEvent visibility false
            return
        }

        if (data.size > 3) {
            val mData = arrayListOf<ModelEvents?>()
            mData.addAll(data.slice(0..2))
            mAdapterEvent.updateAdapter(mData)
        }else {
            mAdapterEvent.updateAdapter(data)
        }
    }

    override fun showLoading(show: Boolean) {
        if (mSwipeRefresh.isRefreshing){
            mSwipeRefresh.isRefreshing = false
        }
        mProgresBar visibility show
    }

    override fun showError(code: Int?, message: String?) {
        toast("$code -> $message")
    }

    override fun setPresenter(presenter: ProductsContracts.Presenter) {
        mPresenter = presenter
    }
}