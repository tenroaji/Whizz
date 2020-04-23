package id.magau.whizz.ui.kelas

import android.content.Intent
import android.os.Bundle
import id.magau.whizz.R
import id.magau.whizz.ui.analisis_penghasilan.AnalisisPenghasilanActivity
import id.magau.whizz.ui.pembayaran.PembayaranActivity
import id.magau.whizz.utils.*
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
        const val KEY_PEMATERI = "PEMATERI"
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (kelasSaya){
            btnBeliKelas visibility false
            val mSession = SessionUtils(this)
            btnAnalisis visibility mSession.getData(KEY_PEMATERI, false)
        }else{
            groupUser visibility false
        }

        groupJabatan visibility false
        
        tvPemateri.text = mPemateri
        tvJabatan visibility false
        val initialName = getInitialName(mPemateri.toUpperCase())
        val iconSize = resources.getDimensionPixelSize(R.dimen.margin_40dp)
        val mColor = ColorGenerator.APP.getColor(mPemateri.length)
        val icon = TextDrawable.builder(this).buildRound(initialName, mColor, iconSize, iconSize)
        imgPemateri.setImageDrawable(icon)

        imgKelas load urlKelas
        tvTitle.text = title

        imgBack.ripple().setOnClickListener {
            onBackPressed()
        }

        val mAdapter = AdapterTabKelas(
            idProduct,supportFragmentManager,kelasSaya
        )
        mViewPager.adapter = mAdapter
        mTabLayout.setupWithViewPager(mViewPager)

        btnAnalisis.setOnClickListener {
            startActivity(Intent(this, AnalisisPenghasilanActivity::class.java).apply {
                putExtra(PembayaranActivity.KEY_ID_PRODUCT,idProduct)
            })
        }

        btnBeliKelas.setOnClickListener{
            startActivity(Intent(this, PembayaranActivity::class.java).apply {
                putExtra(PembayaranActivity.KEY_ID_PRODUCT,idProduct)
            })
        }
    }
}