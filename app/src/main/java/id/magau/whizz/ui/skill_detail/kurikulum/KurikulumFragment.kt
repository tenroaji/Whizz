package id.magau.whizz.ui.skill_detail.kurikulum

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelEvent
import id.magau.whizz.data.model.ModelKurikulum
import id.magau.whizz.ui.skill_detail.pratinjau.PratinjauFragment
import kotlinx.android.synthetic.main.fragment_kurikulum.*

//
//
class KurikulumFragment : Fragment(R.layout.fragment_kurikulum),KurikulumContracts.View {

    companion object {
        const val KEY_ID_PRODUK = "ID_PRODUK"
//        const val KEY_TITLE = "TITLE"
//
//        @JvmStatic
        fun newInstance(idProduct : String): KurikulumFragment {
            val args = Bundle()
            args.putString(KEY_ID_PRODUK, idProduct)
//            args.putString(KEY_TITLE, title)
            val fragment = KurikulumFragment()
            fragment.arguments = args
            return fragment
        }
    }
    private lateinit var mView :View
    private lateinit var mPresenter : KurikulumContracts.Presenter
    private var mAdater = AdapterKurikulum()
    private val idProduk by lazy {
        requireArguments().getString(KEY_ID_PRODUK)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mView = view
        KurikulumPresenter(requireContext(),this)
        mPresenter.loadData(idProduk)

        mRecyclerKurikulum.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdater
        }

//        val data = arrayListOf<ModelKurikulum?>(
//            ModelKurikulum("1","Kurikulum SATU"),
//            ModelKurikulum("2","Kurikulum DUA"),
//            ModelKurikulum("3","Kurikulum TIGA"),
//            ModelKurikulum("4","Kurikulum EMPAT")
//        )
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


    override fun showData(data: ArrayList<ModelKurikulum?>) {
        Log.e("Lapar", "$data")
        mAdater.updateAdapter(data)
    }

    override fun showLoading(show: Boolean) {
//        mView.mProgresBar visibility show
//        if (mView.mSwipeRefresh.isRefreshing) mView.mSwipeRefresh.isRefreshing = false
    }

    override fun showError(code: Int?, message: String?) {
        Toast.makeText(requireContext(), "$message $code", Toast.LENGTH_LONG).show()
    }

    override fun setPresenter(presenter: KurikulumContracts.Presenter) {
        mPresenter = presenter
    }



}
