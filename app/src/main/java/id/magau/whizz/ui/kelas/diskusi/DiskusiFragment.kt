package id.magau.whizz.ui.kelas.diskusi

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.magau.whizz.R
import id.magau.whizz.data.model.*
import id.magau.whizz.ui.kelas.file.AdapterFile
import id.magau.whizz.ui.skill_detail.kurikulum.KurikulumContracts
import kotlinx.android.synthetic.main.fragment_diskusi.*
import kotlinx.android.synthetic.main.fragment_kurikulum.*

//
//
class DiskusiFragment : Fragment(R.layout.fragment_diskusi), DiskusiContracts.View {

    companion object {
        const val KEY_ID_PRODUK = "ID_PRODUK"

        //        const val KEY_TITLE = "TITLE"
//
        @JvmStatic
        fun newInstance(idProduct: String): DiskusiFragment {
            val args = Bundle()
            args.putString(KEY_ID_PRODUK, idProduct)
//            args.putString(KEY_TITLE, title)
            val fragment = DiskusiFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var mView: View
    private lateinit var mPresenter: DiskusiContracts.Presenter
    private var mAdater = AdapterDiskusi()
    private val idProduct by lazy {
        requireArguments().getString(KEY_ID_PRODUK)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DiskusiPresenter(requireContext(), this)
        mPresenter.loadData(idProduct)
        mRecyclerComment.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdater
        }
//
//        val data = arrayListOf<ModelFile?>()
//        for (a in 1 until 25) {
//            data.add(ModelFile("File Tools $a", "", ""))
//        }
//        showData(data)


//        mView = view
//        TokenPresenter(activity!!.applicationContext,this)
//        mPresenter.start()
//
//        mRecyclerBank.setHasFixedSize(true)
//        mRecyclerBank.layoutManager = LinearLayoutManager(requireContext())
//        mRecyclerBank.adapter = mAdapter
//        val mList = mutableListOf<ModelMenu>()
//        mList.add(ModelMenu(R.drawable.logo_bni, "BNI Syariah"))


    }


    override fun showData(data: ArrayList<ModelComments?>) {
        mAdater.updateAdapter(data)
    }

    override fun showLoading(show: Boolean) {

    }

    override fun showError(code: Int?, message: String?) {
        Toast.makeText(requireContext(), "$message $code", Toast.LENGTH_LONG).show()
    }

    override fun setPresenter(presenter: DiskusiContracts.Presenter) {
        mPresenter = presenter
    }


}
