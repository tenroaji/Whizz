package id.magau.whizz.ui.skill_detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.magau.whizz.ui.event_detail.jadwal.JadwalFragment
import id.magau.whizz.ui.skill_detail.kurikulum.KurikulumFragment
import id.magau.whizz.ui.skill_detail.pratinjau.PratinjauFragment


class AdapterTab(private val idProduct : String,private val descSkill : String?, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> PratinjauFragment.newInstance(idProduct,descSkill)
        1 -> KurikulumFragment.newInstance(idProduct)
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