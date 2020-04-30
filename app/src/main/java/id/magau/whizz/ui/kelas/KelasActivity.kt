package id.magau.whizz.ui.kelas

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelProducts
import id.magau.whizz.ui.analisis_penghasilan.AnalisisPenghasilanActivity
import id.magau.whizz.ui.pembayaran.PembayaranActivity
import id.magau.whizz.utils.*
import kotlinx.android.synthetic.main.activity_kelas.*
import kotlinx.android.synthetic.main.item_loading.*

/**
 * Created by Andi Tenroaji Ahmad on 3/12/2020.
 */

class KelasActivity : BaseActivity(R.color.colorWhite,R.layout.activity_kelas),KelasContracts.View {
    companion object {
        const val KEY_ID_PRODUCT = "ID_PRODUCT"
        const val KEY_KELAS_SAYA = "KELAS_SAYA"
        const val KEY_URL_IMG = "URL_IMG"
        const val KEY_TITLE = "TITLE"
        const val KEY_PEMATERI = "PEMATERI"
        const val PERMISSION_WRITE_EXTERNAL = 9999
    }
    val kelasSaya by lazy {
        intent.getBooleanExtra(KEY_KELAS_SAYA,false)
    }

    val mPemateri by lazy {
        intent.getStringExtra(KEY_PEMATERI)
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
    private var mPrice = ""
    private lateinit var mPresenter : KelasContracts.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KelasPresenter(this, this)

        val mSession = SessionUtils(this)
        btnAnalisis visibility mSession.getData(KEY_PEMATERI, false)
        if (kelasSaya){
            mPresenter.loadMySkill(idProduct)
            btnBeliKelas visibility false
        }else{
            groupUser visibility false
            mPresenter.loadData(idProduct)
        }

        groupJabatan visibility false
        

        imgBack.ripple().setOnClickListener {
            onBackPressed()
        }


        btnAnalisis.setOnClickListener {
            startActivity(Intent(this, AnalisisPenghasilanActivity::class.java).apply {
                putExtra(PembayaranActivity.KEY_ID_PRODUCT,idProduct)
            })
        }

        btnBeliKelas.setOnClickListener{
            startActivity(Intent(this, PembayaranActivity::class.java).apply {
                putExtra(PembayaranActivity.KEY_ID_PRODUCT,idProduct)
                putExtra(PembayaranActivity.KEY_TYPE,"0")
                putExtra(PembayaranActivity.KEY_PRICE,mPrice)
            })
        }
    }

    override fun showSkill(data: ModelProducts?) {
        tvPemateri.text = data?.teacher?.name
        tvJabatan visibility false
        val initialName = getInitialName(tvPemateri.text.toString().toUpperCase())
        val iconSize = resources.getDimensionPixelSize(R.dimen.margin_40dp)
        val mColor = ColorGenerator.APP.getColor(tvPemateri.text.toString().length)
        val icon = TextDrawable.builder(this).buildRound(initialName, mColor, iconSize, iconSize)
        imgPemateri.setImageDrawable(icon)

        imgKelas load data?.image
        tvTitle.text = data?.title
        mPrice = rupiah(data?.price)

        val mAdapter = AdapterTabKelas(
            idProduct,supportFragmentManager,kelasSaya,data?.streaming
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

    override fun setPresenter(presenter: KelasContracts.Presenter) {
        mPresenter = presenter
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_WRITE_EXTERNAL -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                } else {
                    toast("Membutuhkan Permission")
                }
                return
            }

            else -> {
                toast("Permission Denied")
            }

        }
    }


}