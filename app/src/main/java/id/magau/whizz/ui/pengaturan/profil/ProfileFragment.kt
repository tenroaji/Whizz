package id.magau.whizz.ui.pengaturan.profil

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import id.magau.whizz.R
import id.magau.whizz.utils.ripple
import id.magau.whizz.utils.toast
import kotlinx.android.synthetic.main.fragment_profil.*
import kotlinx.android.synthetic.main.fragment_profil.view.*

/**
 * Created by Andi Tenroaji Ahmad on 3/13/2020.
 */

class ProfileFragment : Fragment(R.layout.fragment_profil) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewEditAlamat.ripple().setOnClickListener {

        }
        viewEditEmail.ripple().setOnClickListener {

        }
        viewEditKota.ripple().setOnClickListener {

        }
        viewEditNamaLengkap.ripple().setOnClickListener {

        }
        viewEditNamaPengguna.ripple().setOnClickListener {

        }
        viewEditPekerjaan.ripple().setOnClickListener {

        }
        viewEditNoHp.ripple().setOnClickListener {

        }

        view.tvHapus.ripple().setOnClickListener {

        }

        imgEditProfil.ripple().setOnClickListener {

        }


    }
}