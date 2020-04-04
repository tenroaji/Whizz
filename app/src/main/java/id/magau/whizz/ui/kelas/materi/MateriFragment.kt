package id.magau.whizz.ui.kelas.materi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelEvent
import id.magau.whizz.data.model.ModelFile
import id.magau.whizz.data.model.ModelKurikulum
import id.magau.whizz.ui.kelas.file.AdapterFile
import id.magau.whizz.ui.kelas.materi.raitng.RatingActivity
import id.magau.whizz.ui.skill_detail.kurikulum.KurikulumContracts
import id.magau.whizz.utils.ripple
import id.magau.whizz.utils.start
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.fragment_kurikulum.*
import kotlinx.android.synthetic.main.fragment_materi.view.*

//
//
class MateriFragment : Fragment(R.layout.fragment_materi), MateriContracts.View {

    companion object {
        const val KEY_ID_PRODUK = "ID_PRODUK"
        const val KEY_KELAS_SAYA = "KELAS_SAYA"

        @JvmStatic
        fun newInstance(idProduct: String,kelasSaya : Boolean): MateriFragment {
            val args = Bundle()
            args.putString(KEY_ID_PRODUK, idProduct)
            args.putBoolean(KEY_KELAS_SAYA, kelasSaya)
            val fragment = MateriFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var mView: View
    private lateinit var mPresenter: MateriContracts.Presenter
    private var mAdater = AdapterFile()
    private val idProduct by lazy{
        arguments?.getString(KEY_ID_PRODUK)
    }

    val kelasSaya by lazy {
        arguments?.getBoolean(KEY_KELAS_SAYA,false)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mView = view

        mView.apply {
            if (kelasSaya){
                groupRating visibility true
            }

            viewBeriRating.ripple().setOnClickListener {
                startActivity(Intent(requireContext(),RatingActivity::class.java).apply {
                    putExtra(RatingActivity.KEY_ID_PRODUCT,idProduct)
                })
            }
        }



    }


    override fun showData(data: ArrayList<ModelFile?>) {
        mAdater.updateAdapter(data)
    }

    override fun showLoading(show: Boolean) {
//        mView.mProgresBar visibility show
//        if (mView.mSwipeRefresh.isRefreshing) mView.mSwipeRefresh.isRefreshing = false
    }

    override fun showError(code: Int?, message: String?) {
        Toast.makeText(requireContext(), "$message $code", Toast.LENGTH_LONG).show()
    }

    override fun setPresenter(presenter: MateriContracts.Presenter) {
        mPresenter = presenter
    }


}
