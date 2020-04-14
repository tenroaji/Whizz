package id.magau.whizz.ui.event_detail

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelEvents
import id.magau.whizz.utils.*
import kotlinx.android.synthetic.main.activity_event_detail.*
import kotlinx.android.synthetic.main.item_loading.*

/**
 * Created by Andi Tenroaji Ahmad on 3/11/2020.
 */

class EventDetailActivity :BaseActivity(null,R.layout.activity_event_detail),EventDetailContracts.View{
    companion object{
        const val KEY_MY_PRODUCT = "PRODUCT"
        const val KEY_ID_EVENT= "ID_EVENT"
    }

    private lateinit var mPresenter : EventDetailContracts.Presenter
    private val idEvent by lazy {
        intent.getStringExtra(KEY_ID_EVENT)
    }
    private var mWaktu = ""
    private var mTanggal = ""
    private var mAlamat = ""
    private var mKota = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventDetailPresenter(this,this)
        mPresenter.loadEvent(idEvent)
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


    }

    override fun showEvent(data: ModelEvents?) {
        tvHarga.text = rupiah(data?.harga!!)
        tvTitle.text = data.event
        imgEvent load (data.image)
        mTanggal = data.tanggalWeb!!
        mWaktu = data.waktuWeb!!
        mAlamat = data.alamat!!
        mKota = data.kota!!

        val mAdapter = AdapterTab(
            supportFragmentManager,idEvent,mTanggal,mWaktu,mAlamat, mKota
        )
        mViewPager.adapter = mAdapter
        mTabLayout.setupWithViewPager(mViewPager)

    }

    override fun showLoading(show: Boolean) {
        mProgresBar visibility show
    }

    override fun showError(code: Int?, message: String?) {
        toast("$code -> $message")
    }

    override fun showNoData() {
    }

    override fun setPresenter(presenter: EventDetailContracts.Presenter) {
        mPresenter = presenter
    }
}