package id.magau.whizz.ui.skill_search

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelProducts
import id.magau.whizz.ui.main_menu.AdapterSkills
import id.magau.whizz.utils.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_skill.imgFilter
import kotlinx.android.synthetic.main.activity_skill.tvLabelSkill
import kotlinx.android.synthetic.main.activity_skill.tvTitleToolbar
import kotlinx.android.synthetic.main.item_loading.*

/**
 * Created by Andi Tenroaji Ahmad on 3/8/2020.
 */

class SkillSearchActivity : BaseActivity(R.color.colorWhite,R.layout.activity_search),SkillSearchContracts.View {
    companion object {
        const val KEY_SKILL_SAYA = "SKILL_SAYA"
    }

    private val mySkill by lazy {
        intent.getBooleanExtra(KEY_SKILL_SAYA,false)
    }
    private lateinit var mPresenter : SkillSearchContracts.Presenter
    private val mAdapterSkills = AdapterSkills()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SkillSearchPresenter(this,this)

        btnBatal.setOnClickListener {
            finish()
        }

        btnTelusuri.setOnClickListener {
            mPresenter.searchSkill(editSearch.text.toString())
        }

//
//        if (mySkill){
//            mPresenter.loadMySkill()
//        }else{
//            mPresenter.start()
//        }
        val mLayoutManager = LinearLayoutManager(this)
        mRecyclerSkill.layoutManager = mLayoutManager
        mRecyclerSkill.adapter = mAdapterSkills
        mRecyclerSkill.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemPosition = mLayoutManager.findLastVisibleItemPosition()
                if ((mLayoutManager.itemCount - visibleItemPosition) <= 1 && mPresenter.isCanNextPage()) {
                    if (mySkill){
                        mPresenter.loadMySkill()
                    }else{
                        mPresenter.start()
                    }
                }
            }
        })

    }

    override fun showSkill(data: ArrayList<ModelProducts?>) {
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

    override fun setPresenter(presenter: SkillSearchContracts.Presenter) {
        mPresenter = presenter
    }
}