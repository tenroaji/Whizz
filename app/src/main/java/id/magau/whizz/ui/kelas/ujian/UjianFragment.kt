package id.magau.whizz.ui.kelas.ujian

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelFile
import id.magau.whizz.data.model.ModelUjian
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.fragment_ujian.*
import kotlinx.android.synthetic.main.item_loading.view.*

//
//
class UjianFragment : Fragment(R.layout.fragment_ujian), UjianContracts.View {

    companion object {
        //        const val KEY_ID_PRODUK = "id"
//        const val KEY_TITLE = "TITLE"
//
//        @JvmStatic
        fun newInstance(): UjianFragment {
            val args = Bundle()
//            args.putString(KEY_ID_PRODUK, id)
//            args.putString(KEY_TITLE, title)
            val fragment = UjianFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var mView: View
    private lateinit var mPresenter: UjianContracts.Presenter
    private var mAdater = AdapterUjian()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mRecyclerUjian.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdater
        }

        val data = arrayListOf<ModelUjian?>(
            ModelUjian("Ujian Latihan"),
            ModelUjian("Ujian Kompetensi")
            )
        showData(data)


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


    override fun showData(data: ArrayList<ModelUjian?>) {
        mAdater.updateAdapter(data)
    }

    override fun showLoading(show: Boolean) {
        mView.mProgresBar visibility show
    }

    override fun showError(code: Int?, message: String?) {
        Toast.makeText(requireContext(), "$message $code", Toast.LENGTH_LONG).show()
    }

    override fun setPresenter(presenter: UjianContracts.Presenter) {
        mPresenter = presenter
    }


}
