package id.magau.whizz.ui.notif

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelNotif
import id.magau.whizz.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_notif.*

/**
 * Created by Andi Tenroaji Ahmad on 3/10/2020.
 */

class NotifActivity :BaseActivity(R.color.colorWhite,R.layout.activity_notif){
    private val mAdapter = AdapterNotif()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mRecyclerPemberitahuan.layoutManager = LinearLayoutManager(this)
        mRecyclerPemberitahuan.adapter = mAdapter
        val data = arrayListOf(
            ModelNotif("Andi Tenroaji Ahmad","Menghafal Al-Qur'an 30 juz","live","Hari ini"),
            ModelNotif("Andi Tenroaji Ahmad","Menghafal Al-Qur'an 30 juz","live","Hari ini"),
            ModelNotif("Andi Tenroaji Ahmad","Menghafal Al-Qur'an 30 juz","live","Hari ini")
        )
        mAdapter.updateAdapter(data)
        
    }
}