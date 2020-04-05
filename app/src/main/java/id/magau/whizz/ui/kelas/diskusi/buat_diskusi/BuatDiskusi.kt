package id.magau.whizz.ui.kelas.diskusi.buat_diskusi

import android.os.Bundle
import id.magau.whizz.R
import id.magau.whizz.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_buat_disukusi.*


/**
 * Created by siapaSAYA on 4/3/2020
 */

class BuatDiskusi : BaseActivity(layout = R.layout.activity_buat_disukusi) {

    companion object {
        const val KEY_ID_PRODUK = "ID_PRODUK"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btnPostDiskusi.setOnClickListener {

        }
    }
}