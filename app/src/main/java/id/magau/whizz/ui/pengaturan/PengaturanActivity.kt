package id.magau.whizz.ui.pengaturan

import android.os.Bundle
import id.magau.whizz.R
import id.magau.whizz.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_pengaturan.*

/**
 * Created by Andi Tenroaji Ahmad on 3/12/2020.
 */

class PengaturanActivity : BaseActivity(R.color.colorWhite,R.layout.activity_pengaturan) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mAdapter = AdapterTabPengaturan(
            supportFragmentManager
        )
        mViewPager.adapter = mAdapter
        mTabLayout.setupWithViewPager(mViewPager)
    }
}