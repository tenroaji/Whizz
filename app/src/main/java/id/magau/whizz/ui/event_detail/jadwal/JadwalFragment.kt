package id.magau.whizz.ui.event_detail.jadwal

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelEvent
import kotlinx.android.synthetic.main.fragment_jadwal.view.*

//
//
class JadwalFragment : Fragment(R.layout.fragment_jadwal),JadwalContracts.View {

    companion object {
        const val KEY_ID_EVENT = "ID_EVENT"
        const val KEY_ALAMAT = "ALAMAT"
        const val KEY_KOTA = "KOTA"
        const val KET_TANGGAL = "TANGGAL"
        const val KEY_WAKTU = "WAKTU"
//
//        @JvmStatic
        fun newInstance(tanggal : String, waktu : String, kota : String, alamat: String): JadwalFragment {
            val args = Bundle()
            args.putString(KEY_ALAMAT, alamat)
            args.putString(KEY_KOTA, kota)
            args.putString(KET_TANGGAL, tanggal)
            args.putString(KEY_WAKTU, waktu)
            val fragment = JadwalFragment()
            fragment.arguments = args
            return fragment
        }
    }
    private lateinit var mView :View
    private lateinit var mPresenter : JadwalContracts.Presenter
    private val mWaktu by lazy{
        arguments?.getString(KEY_WAKTU)
    }
    private val mTanggal by lazy{
        arguments?.getString(KET_TANGGAL)
    }
    private val mAlamat by lazy{
        arguments?.getString(KEY_ALAMAT)
    }
    private val mKota by lazy{
        arguments?.getString(KEY_KOTA)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mView = view
        mView.apply {
            tvAlamat.text = "${mAlamat?.toUpperCase()}, ${mKota?.toUpperCase()}"
            tvTanggal.text = mTanggal?.replace("-"," ")
            tvTime.text = "$mWaktu WITA"
        }

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
