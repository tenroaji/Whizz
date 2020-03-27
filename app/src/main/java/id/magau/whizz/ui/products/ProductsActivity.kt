package id.magau.whizz.ui.products

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelEvent
import id.magau.whizz.data.model.ModelEventSaya
import id.magau.whizz.data.model.ModelSkills
import id.magau.whizz.ui.event_saya.EventSayaActivity
import id.magau.whizz.ui.event_saya.EventSayaContracts
import id.magau.whizz.ui.main_menu.AdapterEvent
import id.magau.whizz.ui.main_menu.AdapterSkills
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
    private val mAdapterSkills = AdapterSkillSaya()
    private val mAdapterKelas = AdapterSkillSaya()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mRecyclerEventSaya.layoutManager = LinearLayoutManager(this)
        mRecyclerEventSaya.adapter = mAdapterEvent

        mRecyclerKelasSaya.layoutManager = LinearLayoutManager(this)
        mRecyclerKelasSaya.adapter = mAdapterKelas

        mRecyclerSkillSaya.layoutManager = LinearLayoutManager(this)
        mRecyclerSkillSaya.adapter = mAdapterSkills

        tvLabelSemuaSkill.ripple().setOnClickListener {
            startActivity(Intent(this@ProductsActivity, SkillSayaActivity::class.java).apply {
                putExtra(KEY_SKILL,true)
            })
            overridePendingTransition(R.anim.enter, R.anim.exit)
        }
        tvLabelSemuaKelas.ripple().setOnClickListener {
            startActivity(Intent(this@ProductsActivity, SkillSayaActivity::class.java).apply {
                putExtra(KEY_SKILL,false)
            })
            overridePendingTransition(R.anim.enter, R.anim.exit)
        }
        tvLabelSemuaEvent.ripple().setOnClickListener {
            start(EventSayaActivity::class.java)
        }
        val mData1 = arrayListOf(
            ModelSkills("HARD SKILL","The Complete App Design Course - UX, UI and Design Thinking",0,4.4F,"Rp 207.900"),
            ModelSkills("SOFT SKILL","The Complete App Design Course - UX, UI and Design Thinking",0,4.4F,"Rp 207.900"),
            ModelSkills("HARD SKILL","The Complete App Design Course - UX, UI and Design Thinking",0,4.4F,"Rp 207.900"))
        showSkillSaya(mData1)
        val mData2 = arrayListOf(
            ModelEventSaya("The Complete App Design Course - UX, UI and Design Thinking",0,"28 November 2019"),
            ModelEventSaya("The Complete App Design Course - UX, UI and Design Thinking",0,"28 November 2019"),
            ModelEventSaya("The Complete App Design Course - UX, UI and Design Thinking",0,"28 November 2019"))
        showEvent(mData2)
        val mData3 = arrayListOf(
            ModelSkills("HARD SKILL","The Complete App Design Course - UX, UI and Design Thinking",0,4.4F,"Rp 207.900"),
            ModelSkills("SOFT SKILL","The Complete App Design Course - UX, UI and Design Thinking",0,4.4F,"Rp 207.900"),
            ModelSkills("HARD SKILL","The Complete App Design Course - UX, UI and Design Thinking",0,4.4F,"Rp 207.900"))
        showKelasSaya(mData3)


    }

    override fun showSkillSaya(data: ArrayList<ModelSkills>) {
        mAdapterSkills.updateAdapter(data)
    }

    override fun showKelasSaya(data: ArrayList<ModelSkills>) {
        mAdapterKelas.updateAdapter(data)
    }

    override fun showEvent(data: ArrayList<ModelEventSaya>) {
        mAdapterEvent.updateAdapter(data)
    }

    override fun showLoading(show: Boolean) {
        mProgresBar visibility show
    }

    override fun showError(code: Int?, message: String?) {
        toast("$code -> $message")
    }

    override fun setPresenter(presenter: ProductsContracts.Presenter) {
        mPresenter = presenter
    }
}