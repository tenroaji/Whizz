package id.magau.whizz.ui.soal_panel

import android.os.Bundle
import id.magau.whizz.R
import id.magau.whizz.ui.event_detail.AdapterTab
import id.magau.whizz.utils.BaseActivity
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.activity_event_detail.mTabLayout
import kotlinx.android.synthetic.main.activity_event_detail.mViewPager
import kotlinx.android.synthetic.main.activity_ujian.*

/**
 * Created by Andi Tenroaji Ahmad on 3/30/2020.
 */

class SoalPanel :BaseActivity(layout= R.layout.activity_ujian) {
    companion object {
        const val KEY_UJIAN_LATIHAN = "UJIAN_LATIHAN"
    }
    val ujianLatihan by lazy {
        intent.getBooleanExtra(KEY_UJIAN_LATIHAN,true)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (ujianLatihan){
            groupTabs visibility ujianLatihan
            groupActivity visibility !ujianLatihan
            val mAdapter = AdapterTab(
                supportFragmentManager
            )
            mViewPager.adapter = mAdapter
            mTabLayout.setupWithViewPager(mViewPager)
        }else {
            groupActivity visibility !ujianLatihan
            groupTabs visibility ujianLatihan

        }
    }
}