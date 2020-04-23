package id.magau.whizz.ui.analisis_penghasilan.total

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

class TotalFragment : Fragment(R.layout.fragment_total_penghasilan){

    companion object {
        //const val KEY_ID_PRODUK = "id"
//
//        @JvmStatic
        fun newInstance(): TotalFragment {
            val args = Bundle()
//            args.putString(KEY_ID_PRODUK, id)
            val fragment = TotalFragment()
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var mView: View


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val idProduk = arguments?.getString(KEY_ID_PRODUK) ?: ""
        mView = view
    }
}