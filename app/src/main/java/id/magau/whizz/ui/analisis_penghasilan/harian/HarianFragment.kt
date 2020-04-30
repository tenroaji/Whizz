package id.magau.whizz.ui.analisis_penghasilan.harian

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import id.magau.whizz.R

/**
 * Created by Andi Tenroaji Ahmad on 3/12/2020.
 */

class HarianFragment : Fragment(R.layout.fragment_analisis_harian){

    companion object {
        //const val KEY_ID_PRODUK = "id"
//
//        @JvmStatic
        fun newInstance(): HarianFragment {
            val args = Bundle()
//            args.putString(KEY_ID_PRODUK, id)
            val fragment =
                HarianFragment()
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