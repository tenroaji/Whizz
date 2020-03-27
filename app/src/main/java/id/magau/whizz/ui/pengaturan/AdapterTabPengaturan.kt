package id.magau.whizz.ui.pengaturan

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.magau.whizz.ui.event_detail.jadwal.JadwalFragment
import id.magau.whizz.ui.event_detail.mentor.MentorFragment
import id.magau.whizz.ui.pengaturan.kata_sandi.KataSandiFragment
import id.magau.whizz.ui.pengaturan.profil.ProfileFragment


class AdapterTabPengaturan(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> ProfileFragment()
        1 -> KataSandiFragment()
        else -> KataSandiFragment()
    }

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Profile"
        1 -> "Kata Sandi"
        else -> ""
    }

    override fun getCount(): Int {
        return 2
    }
}