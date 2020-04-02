package id.magau.whizz.ui.soal_analisis

import android.os.Bundle
import id.magau.whizz.R
import id.magau.whizz.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_analisis_hasil_latihan.*

/**
 * Created by Andi Tenroaji Ahmad on 3/30/2020.
 */

class SoalAnalisisKompetensi :BaseActivity(layout= R.layout.activity_analisis_hasil_kompetensi) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btnKeluar.setOnClickListener {
            onBackPressed()
        }
    }
}