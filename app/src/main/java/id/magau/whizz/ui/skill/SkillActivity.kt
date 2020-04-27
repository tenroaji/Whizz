package id.magau.whizz.ui.skill

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelProducts
import id.magau.whizz.ui.main_menu.AdapterSkills
import id.magau.whizz.ui.skill_search.SkillSearchActivity
import id.magau.whizz.ui.skill_search.SkillSearchContracts
import id.magau.whizz.utils.*
import kotlinx.android.synthetic.main.activity_skill.*
import kotlinx.android.synthetic.main.activity_skill.imgFilter
import kotlinx.android.synthetic.main.activity_skill.tvLabelSkill
import kotlinx.android.synthetic.main.activity_skill.tvTitleToolbar
import kotlinx.android.synthetic.main.item_loading.*

/**
 * Created by Andi Tenroaji Ahmad on 3/8/2020.
 */

class SkillActivity : BaseActivity(R.color.colorWhite,R.layout.activity_skill),SkillContracts.View {
    companion object {
        const val KEY_SKILL_SAYA = "SKILL_SAYA"
    }

    private val mySkill by lazy {
        intent.getBooleanExtra(KEY_SKILL_SAYA,false)
    }
    private lateinit var mPresenter : SkillContracts.Presenter
    private val mAdapterSkills = AdapterSkills()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SkillPresenter(this,this)
        imgFilter visibility false
        groupFilter visibility false
//        imgSearch visibility false

        imgSearch.ripple().setOnClickListener {
            startActivity(Intent(this,SkillSearchActivity::class.java).apply {
                putExtra(SkillSearchActivity.KEY_SKILL_SAYA,mySkill)
            })
        }


        if (mySkill){
            mPresenter.loadMySkill()
            tvTitleToolbar.text = "Skills Saya"
            tvLabelSkill.text = "Semua Skill Saya"
            imgSearch visibility false
        }else{
            mPresenter.start()
        }
        val mLayoutManager = LinearLayoutManager(this)
        mRecyclerSkill.layoutManager = mLayoutManager
        mRecyclerSkill.adapter = mAdapterSkills
        mRecyclerSkill.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemPosition = mLayoutManager.findLastVisibleItemPosition()
//                Log.e(
//                    "onscroll",
//                    "item = ${mLayoutManager.itemCount} visible = ${visibleItemPosition} total =${mLayoutManager.itemCount - visibleItemPosition}, next = ${mPresenter.isCanNextPage()}"
//                )
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

    override fun setPresenter(presenter: SkillContracts.Presenter) {
        mPresenter = presenter
    }
}