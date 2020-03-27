package id.magau.whizz.ui.skill_detail

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelProducts
import id.magau.whizz.ui.kelas.KelasActivity
import id.magau.whizz.ui.pembayaran.PembayaranActivity
import id.magau.whizz.utils.*
import kotlinx.android.synthetic.main.activity_event_detail.imgBack
import kotlinx.android.synthetic.main.activity_event_detail.mTabLayout
import kotlinx.android.synthetic.main.activity_event_detail.mViewPager
import kotlinx.android.synthetic.main.activity_event_detail.tvDiskon
import kotlinx.android.synthetic.main.activity_event_detail.viewButton
import kotlinx.android.synthetic.main.activity_event_detail.viewPrice
import kotlinx.android.synthetic.main.activity_skill_detail.*
import kotlinx.android.synthetic.main.item_loading.*

/**
 * Created by Andi Tenroaji Ahmad on 3/11/2020.
 */

class SkillDetailActivity :BaseActivity(null,R.layout.activity_skill_detail),SkillDetailContracts.View{
    companion object{
        const val KEY_ID_PRODUCT = "ID_PRODUCT"
        const val KEY_PROMO = "ID_PRODUCT"
    }

    private val idProduct by lazy {
        intent.getStringExtra(KEY_ID_PRODUCT)
    }
    private lateinit var mPresenter : SkillDetailContracts.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SkillDetailPresenter(this,this)
        mPresenter.loadData(idProduct)
        val diskon = false
        if (diskon){
            tvDiskon visibility true
            init()
            val layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    resources.getDimensionPixelSize(R.dimen.margin_64dp)
                )
            )
            layoutParams.topToBottom = tvRating.id
            layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
            layoutParams.marginStart = resources.getDimensionPixelSize(R.dimen.margin_16dp)
            layoutParams.topMargin = resources.getDimensionPixelSize(R.dimen.margin_16dp)
            layoutParams.marginEnd = resources.getDimensionPixelSize(R.dimen.margin_16dp)
            viewPrice.layoutParams = layoutParams
        }
        val product = intent.getBooleanExtra(KEY_PROMO,false)
        if (product){
            viewButton visibility false
            val layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                )
            )
            layoutParams.bottomMargin = resources.getDimensionPixelSize(R.dimen.margin_16dp)
            mViewPager.layoutParams = layoutParams
        }

        imgBack.setOnClickListener {
            onBackPressed()
        }

        btnBeliKelas.setOnClickListener {
            start(PembayaranActivity::class.java)
        }

        btnLihatKelas.setOnClickListener {
            start(KelasActivity::class.java)
        }

        val mAdapter = AdapterTab(
            supportFragmentManager
        )
        mViewPager.adapter = mAdapter
        mTabLayout.setupWithViewPager(mViewPager)
    }

    fun init(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvDiskon.text = Html.fromHtml("<strike>Rp. 1.000.000</strike>", Html.FROM_HTML_MODE_COMPACT);
        } else {
            tvDiskon.text = Html.fromHtml("<strike>Rp. 1.000.000</strike>");
        }
        tvNama.text = "Andi Tenroaji Ahmad"
        tvDesc.text = "Hafiz"
        imgSkill.setBackgroundColor(resources.getColor(R.color.colorPrimary))
        imgPemateri.setBackgroundColor(resources.getColor(R.color.colorWhite))
        tvJenis.text = "HARD SKILLS"
//        tvTitle.text = "Menghafal Al-Qur'an 30 Juz"
//        tvHarga.text = "RP. 500.000"
        tvLokasi.text = "Makassar"
        tvRating.text = "4.4 (100 Peserta)"
    }

    override fun showSkill(data: ModelProducts?) {
        tvTitle.text = data?.title
        tvHarga.text = rupiah(data?.price!!)

    }

    override fun showLoading(show: Boolean) {
//        mProgresBar visibility show
    }

    override fun showError(code: Int?, message: String?) {
        toast("$code -> $message")
    }

    override fun setPresenter(presenter: SkillDetailContracts.Presenter) {
        mPresenter = presenter
    }
}