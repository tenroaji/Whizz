package id.magau.whizz.ui.kelas.ujian.panel.mulai_ujian

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.magau.whizz.R
import kotlinx.android.synthetic.main.fragment_mulai_ujian.view.*

//
//
class MulaiUjianFragment : Fragment() {

    companion object {
        const val KEY_TITLE = "TITLE"
        const val KEY_DATA = "data"
        const val KEY_ID_PRODUCT = "ID_PRODUCT"

        @JvmStatic
        fun newInstance(idProduct:String, title:String): MulaiUjianFragment {
            val args = Bundle()
            args.putString(KEY_TITLE, title)
            args.putSerializable(KEY_ID_PRODUCT, idProduct)
//            args.putString(KEY_JENIS, jenis)
            val fragment = MulaiUjianFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mulai_ujian, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val title = arguments?.getString(KEY_TITLE)
//        val idProduct = arguments?.getString(KEY_ID_PRODUCT)
        view.btnMulai.setOnClickListener {
//            startActivity(Intent(context, AmbangBatasActivity::class.java).apply {
//                putExtra(KEY_TITLE,title)
//                putExtra(KEY_ID_PRODUCT,idProduct)
//            })
//            (context as AppCompatActivity).overridePendingTransition(R.anim.enter, R.anim.exit)
        }
        view.btnBatal.setOnClickListener {
            activity?.onBackPressed()
        }

    }

}
