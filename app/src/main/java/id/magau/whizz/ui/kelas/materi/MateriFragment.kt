package id.magau.whizz.ui.kelas.materi

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
import id.magau.whizz.ui.skill_detail.kurikulum.KurikulumContracts
import kotlinx.android.synthetic.main.fragment_kurikulum.*

//
//
class MateriFragment : Fragment(R.layout.fragment_materi), MateriContracts.View {

    companion object {
        //        const val KEY_ID_PRODUK = "id"
//        const val KEY_TITLE = "TITLE"
//
//        @JvmStatic
        fun newInstance(): MateriFragment {
            val args = Bundle()
//            args.putString(KEY_ID_PRODUK, id)
//            args.putString(KEY_TITLE, title)
            val fragment = MateriFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var mView: View
    private lateinit var mPresenter: MateriContracts.Presenter
    private var mAdater = AdapterFile()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        mRecyclerKurikulum.apply {
//            layoutManager = LinearLayoutManager(requireContext())
//            adapter = mAdater
//        }
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
