package id.magau.whizz.ui.event_detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.magau.whizz.ui.event_detail.jadwal.JadwalFragment
import id.magau.whizz.ui.event_detail.mentor.MentorFragment


class AdapterTab(fm: FragmentManager,private val idEvent : String,private val tanggal :String, private val waktu : String,
                 private val alamat:String, private val kota : String) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> JadwalFragment.newInstance(tanggal,waktu,kota,alamat)
        1 -> MentorFragment.newInstance()
        else -> MentorFragment.newInstance()
    }

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Jadwal"
        1 -> "Mentor"
        else -> ""
    }

    override fun getCount(): Int {
        return 2
    }
}