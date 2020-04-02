package id.magau.whizz.ui.event_detail

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import id.magau.whizz.R
import id.magau.whizz.utils.BaseActivity
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.activity_event_detail.*

/**
 * Created by Andi Tenroaji Ahmad on 3/11/2020.
 */

class EventDetailActivity :BaseActivity(null,R.layout.activity_event_detail){
    companion object{
        const val KEY_MY_PRODUCT = "PRODUCT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val product = intent.getBooleanExtra(KEY_MY_PRODUCT,false)
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

        val mAdapter = AdapterTab(
            supportFragmentManager
        )
        mViewPager.adapter = mAdapter
        mTabLayout.setupWithViewPager(mViewPager)
    }
}