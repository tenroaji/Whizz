package id.magau.whizz.ui.products

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelEvent
import id.magau.whizz.data.model.ModelEventSaya
import id.magau.whizz.data.model.ModelProducts
import id.magau.whizz.data.model.ModelSkills
import id.magau.whizz.ui.event_saya.EventSayaActivity
import id.magau.whizz.ui.event_saya.EventSayaContracts
import id.magau.whizz.ui.main_menu.AdapterEvent
import id.magau.whizz.ui.main_menu.AdapterSkills
import id.magau.whizz.ui.skill.SkillActivity
import id.magau.whizz.ui.skill.SkillActivity.Companion.KEY_SKILL_SAYA
import id.magau.whizz.ui.skill_saya.AdapterSkillSaya
import id.magau.whizz.ui.skill_saya.SkillSayaActivity
import id.magau.whizz.ui.skill_saya.SkillSayaActivity.Companion.KEY_SKILL
import id.magau.whizz.utils.*
import kotlinx.android.synthetic.main.activty_product.*
import kotlinx.android.synthetic.main.item_loading.*

/**
 * Created by Andi Tenroaji Ahmad on 3/10/2020.
 */

class ProductsActivity : BaseActivity(R.color.colorWhite, R.layout.activty_product),
    ProductsContracts.View {
    private lateinit var mPresenter : ProductsContracts.Presenter
    private val mAdapterEvent = AdapterEventSaya()
    private val mAdapterSkills = AdapterSkills()
    private val mAdapterKelas = AdapterSkills()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ProductsPresenter(this,this)
        mPresenter.start()

        groupEvent visibility false
        groupKelas visibility false
//        groupSkill visibility false
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
        }

    }

    override fun showSkillSaya(data: ArrayList<ModelProducts?>) {
        mAdapterSkills.updateAdapter(data)
    }

    override fun showKelasSaya(data: ArrayList<ModelProducts?>) {
        mAdapterKelas.updateAdapter(data)
    }

    override fun showEvent(data: ArrayList<ModelEventSaya>) {
        mAdapterEvent.updateAdapter(data)
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