package id.magau.whizz.ui.kelas

import android.content.Intent
import android.os.Bundle
import id.magau.whizz.R
import id.magau.whizz.ui.pembayaran.PembayaranActivity
import id.magau.whizz.ui.skill_detail.SkillDetailActivity
import id.magau.whizz.utils.BaseActivity
import id.magau.whizz.utils.load
import id.magau.whizz.utils.ripple
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.activity_kelas.*

/**
 * Created by Andi Tenroaji Ahmad on 3/12/2020.
 */

class KelasActivity : BaseActivity(R.color.colorWhite,R.layout.activity_kelas) {
    companion object {
        const val KEY_ID_PRODUCT = "ID_PRODUCT"
        const val KEY_KELAS_SAYA = "KELAS_SAYA"
        const val KEY_URL_IMG = "URL_IMG"
        const val KEY_TITLE = "TITLE"
    }

    val kelasSaya by lazy {
        intent.getBooleanExtra(KEY_KELAS_SAYA,false)
    }

    val idProduct by lazy {
        intent.getStringExtra(KEY_ID_PRODUCT)
    }

    val title by lazy {
        intent.getStringExtra(KEY_TITLE)
    }

    val urlKelas by lazy {
        intent.getStringExtra(KEY_URL_IMG)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (kelasSaya){
            btnBeliKelas visibility false
        }

        imgKelas load urlKelas
        tvTitle.text = title

        imgBack.ripple().setOnClickListener {
            onBackPressed()
        }

        val mAdapter = AdapterTabKelas(
            supportFragmentManager,kelasSaya
        )
        mViewPager.adapter = mAdapter
        mTabLayout.setupWithViewPager(mViewPager)

        btnBeliKelas.setOnClickListener{
            startActivity(Intent(this, PembayaranActivity::class.java).apply {
                putExtra(PembayaranActivity.KEY_ID_PRODUCT,idProduct)
            })
        }
    }
}