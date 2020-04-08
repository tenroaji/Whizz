package id.magau.whizz.ui.kelas.diskusi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.magau.whizz.R
import id.magau.whizz.data.model.*
import id.magau.whizz.ui.kelas.diskusi.buat_diskusi.BuatDiskusi
import id.magau.whizz.utils.*
import kotlinx.android.synthetic.main.fragment_diskusi.*
import kotlinx.android.synthetic.main.fragment_diskusi.imgUser
import kotlinx.android.synthetic.main.fragment_diskusi.view.*
import kotlinx.android.synthetic.main.item_loading.view.*

//
//
class DiskusiFragment : Fragment(R.layout.fragment_diskusi), DiskusiContracts.View {

    companion object {
        const val KEY_ID_PRODUK = "ID_PRODUK"
        const val KEY_KELAS_SAYA = "KELAS_SAYA"

        //        const val KEY_TITLE = "TITLE"
//
        @JvmStatic
        fun newInstance(idProduct: String,kelasSaya : Boolean): DiskusiFragment {
            val args = Bundle()
            args.putString(KEY_ID_PRODUK, idProduct)
            args.putBoolean(KEY_KELAS_SAYA, kelasSaya)
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

    private val kelasSaya by lazy {
        requireArguments().getBoolean(KEY_KELAS_SAYA,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mView = view

        mView.groupDisksui visibility kelasSaya


        DiskusiPresenter(requireContext(), this)
        mPresenter.loadData(idProduct)
        mView.mRecyclerComment.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdater
        }
        mAdater.updateKelasSaya(kelasSaya)
        val session = SessionUtils(requireContext())
        val nama = session.getData(SessionUtils.PREF_KEY_NAME, "")
        val initialName = getInitialName(nama.toUpperCase())
        val iconSize = resources.getDimensionPixelSize(R.dimen.margin_28dp)
        val mColor = ColorGenerator.APP.getColor(nama.length)
        val icon = TextDrawable.builder(requireContext()).buildRound(initialName, mColor, iconSize, iconSize)
        mView.imgUser.setImageDrawable(icon)

        mView.viewBuatDiskusi.ripple().setOnClickListener {
            startActivity(Intent(requireContext(),BuatDiskusi::class.java).apply {
                putExtra(BuatDiskusi.KEY_ID_PRODUK,idProduct)
            })
        }

        mView.mSwipeRefresh.setColorSchemeColors(resources.getColor(R.color.colorPrimary))
        mView.mSwipeRefresh.setOnRefreshListener {
            mPresenter.loadData(idProduct)
        }

    }


    override fun showData(data: ArrayList<ModelComments?>) {
        mAdater.updateAdapter(data)
    }

    override fun showLoading(show: Boolean) {
        if (mSwipeRefresh.isRefreshing) mSwipeRefresh.isRefreshing = false
        mView.mProgresBar visibility show

    }

    override fun showError(code: Int?, message: String?) {
        Toast.makeText(requireContext(), "$message $code", Toast.LENGTH_LONG).show()
    }

    override fun setPresenter(presenter: DiskusiContracts.Presenter) {
        mPresenter = presenter
    }


}
