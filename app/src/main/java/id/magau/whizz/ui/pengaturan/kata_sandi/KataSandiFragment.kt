package id.magau.whizz.ui.pengaturan.kata_sandi

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import id.magau.whizz.R
import id.magau.whizz.utils.ripple
import id.magau.whizz.utils.start
import kotlinx.android.synthetic.main.fragment_kata_sandi.view.*

/**
 * Created by Andi Tenroaji Ahmad on 3/12/2020.
 */

class KataSandiFragment : Fragment(R.layout.fragment_kata_sandi){

    companion object {
        //const val KEY_ID_PRODUK = "id"
//
//        @JvmStatic
        fun newInstance(): KataSandiFragment {
            val args = Bundle()
//            args.putString(KEY_ID_PRODUK, id)
            val fragment = KataSandiFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val idProduk = arguments?.getString(KEY_ID_PRODUK) ?: ""
        view.viewEditPassword.ripple()
        view.viewEditPassword.setOnClickListener {
            start(KataSandiActivity::class.java)
        }
    }
}