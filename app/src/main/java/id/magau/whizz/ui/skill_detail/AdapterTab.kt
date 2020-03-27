package id.magau.whizz.ui.skill_detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.magau.whizz.ui.event_detail.jadwal.JadwalFragment
import id.magau.whizz.ui.event_detail.mentor.MentorFragment
import id.magau.whizz.ui.skill_detail.kurikulum.KurikulumFragment
import id.magau.whizz.ui.skill_detail.pratinjau.PratinjauFragment


class AdapterTab(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> PratinjauFragment.newInstance()
        1 -> KurikulumFragment.newInstance()
        else -> JadwalFragment.newInstance()
    }

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Pratinjau"
        1 -> "Kurikulum"
        else -> ""
    }

    override fun getCount(): Int {
        return 2
    }
}