package id.magau.whizz.ui.kelas

import android.os.Bundle
import id.magau.whizz.R
import id.magau.whizz.utils.BaseActivity
import id.magau.whizz.utils.ripple
import kotlinx.android.synthetic.main.activity_kelas.*

/**
 * Created by Andi Tenroaji Ahmad on 3/12/2020.
 */

class KelasActivity : BaseActivity(R.color.colorWhite,R.layout.activity_kelas) {
    companion object {
        const val KEY_KELAS_SAYA = "KELAS_SAYA"
    }

    val kelasSaya by lazy {
        intent.getBooleanExtra(KEY_KELAS_SAYA,false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imgBack.ripple().setOnClickListener {
            onBackPressed()
        }

        val mAdapter = AdapterTabKelas(
            supportFragmentManager,kelasSaya
        )
        mViewPager.adapter = mAdapter
        mTabLayout.setupWithViewPager(mViewPager)
    }
}