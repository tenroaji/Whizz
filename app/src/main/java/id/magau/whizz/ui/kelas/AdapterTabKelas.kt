package id.magau.whizz.ui.kelas

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.magau.whizz.ui.event_detail.jadwal.JadwalFragment
import id.magau.whizz.ui.event_detail.mentor.MentorFragment
import id.magau.whizz.ui.kelas.diskusi.DiskusiFragment
import id.magau.whizz.ui.kelas.file.FileFragment
import id.magau.whizz.ui.kelas.ujian.UjianFragment
import id.magau.whizz.ui.pengaturan.kata_sandi.KataSandiFragment
import id.magau.whizz.ui.pengaturan.profil.ProfileFragment


class AdapterTabKelas(fm: FragmentManager, private val terbeli : Boolean) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment =
//    {
//        if (position == 0){
//           return FileFragment.newInstance()
//        }else if (position == 1){
//            DiskusiFragment.newInstance()
//        }else if (position == 2){
//           return FileFragment.newInstance()
//        }else if (position == 3){
//            UjianFragment.newInstance()
//        }else{
//           return FileFragment.newInstance()
//        }


        when (position) {
        0 -> FileFragment.newInstance()
        1 -> DiskusiFragment.newInstance()
        2 -> FileFragment.newInstance()
        3 -> UjianFragment.newInstance()
        else -> KataSandiFragment()
    }

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Materi"
        1 -> "Diskusi"
        2 -> "File"
        3 -> "Ujian"
        else -> ""
    }

    override fun getCount(): Int {
        return if (terbeli){
            4
        }else
            3
    }
}