package id.magau.whizz.ui.analisis_penghasilan

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.magau.whizz.ui.analisis_penghasilan.bulanan.BulananFragment
import id.magau.whizz.ui.analisis_penghasilan.harian.HarianFragment
import id.magau.whizz.ui.analisis_penghasilan.total.TotalFragment
import id.magau.whizz.ui.event_detail.jadwal.JadwalFragment
import id.magau.whizz.ui.event_detail.mentor.MentorFragment
import id.magau.whizz.ui.pengaturan.kata_sandi.KataSandiFragment
import id.magau.whizz.ui.pengaturan.profil.ProfileFragment


class AdapterTab(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> TotalFragment.newInstance()
        1 -> BulananFragment.newInstance()
        2 -> HarianFragment.newInstance()
        else -> KataSandiFragment()
    }

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Total"
        1 -> "Bulanan"
        2 -> "Harian"
        else -> ""
    }

    override fun getCount(): Int {
        return 3
    }
}