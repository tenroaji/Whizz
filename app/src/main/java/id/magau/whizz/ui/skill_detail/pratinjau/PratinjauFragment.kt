package id.magau.whizz.ui.skill_detail.pratinjau

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelFAQ
import id.magau.whizz.data.model.ModelFile
import id.magau.whizz.data.model.ModelTestimoni
import id.magau.whizz.ui.faq.FaqActivity
import id.magau.whizz.utils.ripple
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.fragment_pratinjau.*
import kotlinx.android.synthetic.main.fragment_pratinjau.view.*
import kotlinx.android.synthetic.main.item_loading.view.*

//
//
class PratinjauFragment : Fragment(R.layout.fragment_pratinjau), PratinjauContracts.View {

    companion object {
        const val KEY_ID_PRODUK = "ID_PRODUK"
                const val KEY_DESC = "DESC"
//
//        @JvmStatic
        fun newInstance(idProduk: String, desc:String?): PratinjauFragment {
            val args = Bundle()
            args.putString(KEY_ID_PRODUK, idProduk)
            args.putString(KEY_DESC, desc)
            val fragment = PratinjauFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var mView: View
    private lateinit var mPresenter: PratinjauContracts.Presenter
    private var mAdapterFAQ = AdapterFAQ()
    private var mAdapterTestimoni = AdapterTestimoni()


    private val idProduk by lazy {
        arguments?.getString(KEY_ID_PRODUK)!!
    }

    private val mDesc by lazy {
        arguments?.getString(KEY_DESC)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mView = view
        mView.tvDesc.text = mDesc
        PratinjauPresenter(requireContext(), this)
        mPresenter.loadFAQ(idProduk)

        mRecyclerFAQ.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapterFAQ
        }
        mRecyclerTestimoni.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapterTestimoni
        }

        groupTestimoni visibility false

        tvLabelSemuaFAQ.ripple().setOnClickListener {
            startActivity(Intent(requireContext(), FaqActivity::class.java).apply {
                putExtra(KEY_ID_PRODUK,idProduk)
            })
            (requireActivity() as AppCompatActivity).overridePendingTransition(R.anim.enter, R.anim.exit)
        }
//        val data2 = arrayListOf<ModelFAQ?>()
//        for (a in 1 until 25) {
//            data2.add(ModelFAQ("FAQ $a", "Description $a"))
//        }
//        showFAQ(data2)

    }


    override fun showData(data: ArrayList<ModelFile?>) {

    }

    override fun showFAQ(data: ArrayList<ModelFAQ?>) {
        if (data.size > 3) {
            val mData = arrayListOf<ModelFAQ?>()
            mData.addAll(data.slice(0..2))
            mAdapterFAQ.updateAdapter(mData)
        }else {
            mAdapterFAQ.updateAdapter(data)
        }
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
