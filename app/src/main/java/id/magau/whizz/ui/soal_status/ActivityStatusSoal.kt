package id.magau.whizz.ui.soal_status

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import id.magau.whizz.R
import id.magau.whizz.data.model.History
import id.magau.whizz.utils.BaseActivity
import id.magau.whizz.utils.GridSpacingItemDecoration
import kotlinx.android.synthetic.main.activity_status_soal.*

/**
 * Created by Andi Tenroaji Ahmad on 1/24/2020.
 */

class ActivityStatusSoal:BaseActivity(layout=R.layout.activity_status_soal){
    companion object{
        const val KEY_STATUS_SOAL = "STATUS_SOAL"
        const val KEY_DURASI = "DURASI"
    }

    private var mWidthMenu = 0
    private var mHeightMenu = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        calculateMenuSize()
        val mData = intent.getSerializableExtra(KEY_STATUS_SOAL) as List<History>
        val mAdapter = AdapterStatusSoal()
        mRecyclerStatusSoal.layoutManager = GridLayoutManager(this,5)
        mRecyclerStatusSoal.addItemDecoration(
            GridSpacingItemDecoration(
                5,
                resources.getDimensionPixelSize(R.dimen.margin_8dp),
                true
            )
        )
        mRecyclerStatusSoal.adapter = mAdapter
        mAdapter.updateAdapter(mData,mWidthMenu,mHeightMenu)
    }

    private fun calculateMenuSize() {
        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        val margins = resources.getDimension(R.dimen.margin_8dp).toInt() * 6
        val realWitdh = (width - (margins)) / 6
        mWidthMenu = realWitdh
        mHeightMenu = (realWitdh / 0.95F).toInt()
    }
}