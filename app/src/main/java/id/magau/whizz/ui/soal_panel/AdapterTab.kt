package id.magau.whizz.ui.soal_panel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.magau.whizz.ui.event_detail.jadwal.JadwalFragment
import id.magau.whizz.ui.event_detail.mentor.MentorFragment
import id.magau.whizz.ui.soal_panel.attention.AttentionFragment
import id.magau.whizz.ui.soal_panel.riwayat.RiwayatFragment


class AdapterTab(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> AttentionFragment()
        1 -> RiwayatFragment()
        else -> JadwalFragment()
    }

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Latihan"
        1 -> "Riwayat"
        else -> ""
    }

    override fun getCount(): Int {
        return 2
    }
}