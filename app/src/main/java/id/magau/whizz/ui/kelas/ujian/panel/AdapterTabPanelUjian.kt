package id.magau.whizz.ui.kelas.ujian.panel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.magau.whizz.ui.kelas.ujian.panel.mulai_ujian.MulaiUjianFragment
import id.magau.whizz.ui.kelas.ujian.panel.riwayat.RiwayatFragment


class AdapterTabPanelUjian( fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> MulaiUjianFragment()
        1 -> RiwayatFragment()
        else -> RiwayatFragment()
    }

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Latihan"
        1 -> "Riwayat"
        else -> ""
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}