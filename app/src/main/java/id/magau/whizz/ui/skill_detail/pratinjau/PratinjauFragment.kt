package id.magau.whizz.ui.skill_detail.pratinjau

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelFAQ
import id.magau.whizz.data.model.ModelFile
import id.magau.whizz.data.model.ModelTestimoni
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.fragment_kurikulum.*
import kotlinx.android.synthetic.main.fragment_pratinjau.*
import kotlinx.android.synthetic.main.item_loading.view.*

//
//
class PratinjauFragment : Fragment(R.layout.fragment_pratinjau), PratinjauContracts.View {

    companion object {
        //        const val KEY_ID_PRODUK = "id"
//        const val KEY_TITLE = "TITLE"
//
//        @JvmStatic
        fun newInstance(): PratinjauFragment {
            val args = Bundle()
//            args.putString(KEY_ID_PRODUK, id)
//            args.putString(KEY_TITLE, title)
            val fragment = PratinjauFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var mView: View
    private lateinit var mPresenter: PratinjauContracts.Presenter
    private var mAdapterFAQ = AdapterFAQ()
    private var mAdapterTestimoni = AdapterTestimoni()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mRecyclerFAQ.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapterFAQ
        }
        mRecyclerTestimoni.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapterTestimoni
        }

        val data = arrayListOf<ModelTestimoni?>()
        for (a in 1 until 25) {
            data.add(ModelTestimoni("User $a", "Pekerjaan $a","","Description $a"))
        }
        showTestimoni(data)

        val data2 = arrayListOf<ModelFAQ?>()
        for (a in 1 until 25) {
            data2.add(ModelFAQ("FAQ $a", "Description $a"))
        }
        showFAQ(data2)


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

    }

    override fun showFAQ(data: ArrayList<ModelFAQ?>) {
        mAdapterFAQ.updateAdapter(data)
    }

    override fun showTestimoni(data: ArrayList<ModelTestimoni?>) {
        mAdapterTestimoni.updateAdapter(data)
    }

    override fun showLoading(show: Boolean) {
        mView.mProgresBar visibility show
//        if (mView.mSwipeRefresh.isRefreshing) mView.mSwipeRefresh.isRefreshing = false
    }

    override fun showError(code: Int?, message: String?) {
        Toast.makeText(requireContext(), "$message $code", Toast.LENGTH_LONG).show()
    }

    override fun setPresenter(presenter: PratinjauContracts.Presenter) {
        mPresenter = presenter
    }


}
