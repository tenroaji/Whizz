package id.magau.whizz.ui.kelas.ujian.panel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.magau.whizz.R
import id.magau.whizz.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_panel_ujian.*

/**
 * Created by Andi Tenroaji Ahmad on 1/13/2020.
 */

class PanelUjianActivity : BaseActivity(layout= R.layout.activity_panel_ujian) {
    companion object {
        const val KEY_TITLE = "TITLE"
        const val KEY_ONLINE = "ONLINE"
        const val KEY_ID_PRODUCT = "ID_PRODUCT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewPager.adapter = AdapterTabPanelUjian(supportFragmentManager)
        mTabLayout.setupWithViewPager(mViewPager)
    }

    //    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        }
//        mViewPager.adapter = AdapterTabPanelProduct(
//            intent.getBooleanExtra(KEY_ONLINE, intent.getBooleanExtra(KEY_ONLINE,false)),
//            intent.getStringExtra(KEY_ID_PRODUCT),
//            intent.getStringExtra(KEY_TITLE)
//            , supportFragmentManager
//        )
//        mTabLayout.setupWithViewPager(mViewPager)
//    }
}