package id.magau.whizz.ui.event_detail.jadwal

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelEvent

//
//
class JadwalFragment : Fragment(R.layout.fragment_jadwal),JadwalContracts.View {

    companion object {
//        const val KEY_ID_PRODUK = "id"
//        const val KEY_TITLE = "TITLE"
//
//        @JvmStatic
        fun newInstance(): JadwalFragment {
            val args = Bundle()
//            args.putString(KEY_ID_PRODUK, id)
//            args.putString(KEY_TITLE, title)
            val fragment = JadwalFragment()
            fragment.arguments = args
            return fragment
        }
    }
    private lateinit var mView :View
    private lateinit var mPresenter : JadwalContracts.Presenter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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


    override fun showEvent(data: ArrayList<ModelEvent>) {

    }

    override fun showLoading(show: Boolean) {
//        mView.mProgresBar visibility show
//        if (mView.mSwipeRefresh.isRefreshing) mView.mSwipeRefresh.isRefreshing = false
    }

    override fun showError(code: Int?, message: String?) {
        Toast.makeText(requireContext(), "$message $code", Toast.LENGTH_LONG).show()
    }

    override fun setPresenter(presenter: JadwalContracts.Presenter) {
        mPresenter = presenter
    }



}
