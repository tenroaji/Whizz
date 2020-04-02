package id.magau.whizz.ui.skill

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelProducts
import id.magau.whizz.data.model.ModelSkills
import id.magau.whizz.ui.main_menu.AdapterSkills
import id.magau.whizz.utils.BaseActivity
import id.magau.whizz.utils.clicked
import id.magau.whizz.utils.toast
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.activity_skill.*
import kotlinx.android.synthetic.main.item_loading.*

/**
 * Created by Andi Tenroaji Ahmad on 3/8/2020.
 */

class SkillActivity : BaseActivity(R.color.colorWhite,R.layout.activity_skill),SkillContracts.View {
    private lateinit var mPresenter : SkillContracts.Presenter
    private val mAdapterSkills = AdapterSkills()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SkillPresenter(this,this)
        mPresenter.start()
        mRecyclerSkill.layoutManager = LinearLayoutManager(this)
        mRecyclerSkill.adapter = mAdapterSkills


    }

    override fun showSkill(data: ArrayList<ModelProducts?>) {
        mAdapterSkills.updateAdapter(data)
    }

    override fun showLoading(show: Boolean) {
        mProgresBar visibility show
    }

    override fun showError(code: Int?, message: String?) {
        toast("$code -> $message")
    }

    override fun setPresenter(presenter: SkillContracts.Presenter) {
        mPresenter = presenter
    }
}