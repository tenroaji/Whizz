package id.magau.whizz.ui.kelas.ujian.panel.riwayat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelRiwayat
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.fragment_riwayat.view.*
import kotlinx.android.synthetic.main.item_loading.*

//
//
class RiwayatFragment : Fragment(), RiwayatContracts.View {

    companion object {
        const val KEY_ID_PRODUK = "ID_PRODUK"
        const val KEY_DATA = "data"

        @JvmStatic
        fun newInstance(idProduk: String): RiwayatFragment {
            val args = Bundle()
            args.putString(KEY_ID_PRODUK, idProduk)
//            args.putSerializable(KEY_DATA, data)
//            args.putString(KEY_JENIS, jenis)
            val fragment = RiwayatFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var mPresenter: RiwayatContracts.Presenter
    private val mAdapter = AdapterRiwayat()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_riwayat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idProduk = arguments?.getString(KEY_ID_PRODUK) ?: ""

        RiwayatPresenter(activity!!.baseContext, this)
        mPresenter.getRiwayat(idProduk)
        val mLayoutManager = LinearLayoutManager(context)
        view.mRecyclerRiwayat.layoutManager = mLayoutManager
        view.mRecyclerRiwayat.adapter = mAdapter

        view.mRecyclerRiwayat.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemPosition = mLayoutManager.findLastVisibleItemPosition()
//                Log.e(
//                    "onscroll",
//                    "item = ${mLayoutManager.itemCount} visible = ${visibleItemPosition} total =${mLayoutManager.itemCount - visibleItemPosition}, next = ${mPresenter.isCanNextPage()}"
//                )
                if ((mLayoutManager.itemCount - visibleItemPosition) <= 1 && mPresenter.isCanNextPage()) {
                    mPresenter.getRiwayat(idProduk)
                }
            }

        })


    }

    override fun showRiwayat(data: MutableList<ModelRiwayat?>) {
        mAdapter.updateAdapter(data)
    }

    override fun showLoading(show: Boolean) {
        mProgresBar visibility show
    }

    override fun showNextLoading(show: Boolean) {
        mAdapter.setLoading(show)
    }

    override fun showNoData() {
        mAdapter.clearList()
        showToast("Data belum tersedia")
    }

    override fun showError(code: Int, message: String?) {
        Toast.makeText(activity?.baseContext, "$code , $message", Toast.LENGTH_LONG).show()
    }

    override fun showToast(message: String?) {
        Toast.makeText(activity?.baseContext, message, Toast.LENGTH_LONG).show()
    }

    override fun setPresenter(presenter: RiwayatContracts.Presenter) {
        mPresenter = presenter
    }

}
