package id.magau.whizz.ui.skill_saya

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelProducts
import id.magau.whizz.data.model.ModelSkills
import id.magau.whizz.utils.BaseActivity
import id.magau.whizz.utils.toast
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.activity_skill.*
import kotlinx.android.synthetic.main.item_loading.*

/**
 * Created by Andi Tenroaji Ahmad on 3/8/2020.
 */

class SkillSayaActivity : BaseActivity(R.color.colorWhite, R.layout.activity_skill),
    SkillSayaContracts.View {
    companion object {
        const val KEY_SKILL = "SKILL"
    }

    private lateinit var mPresenter: SkillSayaContracts.Presenter
    private val mAdapterSkills = AdapterSkillSaya()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val skill = intent.getBooleanExtra(KEY_SKILL, false)
        if (skill) {
            tvTitleToolbar.text = "Skills Saya"
            tvLabelSkill.text = "Skills Saya"
        }else {
            tvTitleToolbar.text = "Kelas Saya"
            tvLabelSkill.text = "Kelas Saya"
        }
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

    override fun setPresenter(presenter: SkillSayaContracts.Presenter) {
        mPresenter = presenter
    }
}