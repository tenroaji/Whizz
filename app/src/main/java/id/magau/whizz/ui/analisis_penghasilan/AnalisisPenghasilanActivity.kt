package id.magau.whizz.ui.analisis_penghasilan

import android.os.Bundle
import id.magau.whizz.R
import id.magau.whizz.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_pengaturan.*

/**
 * Created by Andi Tenroaji Ahmad on 3/12/2020.
 */

class AnalisisPenghasilanActivity : BaseActivity(R.color.colorWhite,R.layout.activity_analisis_penghasilan) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mAdapter = AdapterTab(
            supportFragmentManager
        )
        mViewPager.adapter = mAdapter
        mTabLayout.setupWithViewPager(mViewPager)
    }
}