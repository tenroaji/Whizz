package id.magau.whizz.ui.kelas.file

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelEvent
import id.magau.whizz.data.model.ModelFile
import id.magau.whizz.data.model.ModelKurikulum
import id.magau.whizz.ui.skill_detail.kurikulum.KurikulumContracts
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.fragment_kurikulum.*
import kotlinx.android.synthetic.main.fragment_kurikulum.view.*
import kotlinx.android.synthetic.main.item_loading.view.*

//
//
class FileFragment : Fragment(R.layout.fragment_kurikulum), FileContracts.View {

    companion object {
        const val KEY_ID_PRODUK = "ID_PRODUK"
        const val KEY_KELAS_SAYA = "KELAS_SAYA"

        //        const val KEY_TITLE = "TITLE"
//
        @JvmStatic
        fun newInstance(idProduct: String,kelasSaya : Boolean): FileFragment {
            val args = Bundle()
            args.putString(KEY_ID_PRODUK, idProduct)
            args.putBoolean(KEY_KELAS_SAYA, kelasSaya)
            val fragment = FileFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val idProduct by lazy {
        requireArguments().getString(KEY_ID_PRODUK)!!
    }

    private val kelasSaya by lazy {
        requireArguments().getBoolean(KEY_KELAS_SAYA,false)
    }

    private lateinit var mView: View
    private lateinit var mPresenter: FileContracts.Presenter
    private var mAdater = AdapterFile()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FilePresenter(requireContext(),this)
        mPresenter.loadData(idProduct)
        mView = view
        mView.apply {
            tvTitle visibility false
            mRecyclerKurikulum.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = mAdater
            }
        }
//
//        val data = arrayListOf<ModelFile?>()
//        for (a in 1 until 25) {
//            data.add(ModelFile("File Tools $a", "", ""))
//        }
//        showData(data)





}


override fun showData(data: ArrayList<ModelFile?>) {
    mAdater.updateAdapter(data)
}

override fun showLoading(show: Boolean) {
        mView.mProgresBar visibility show
//        if (mView.mSwipeRefresh.isRefreshing) mView.mSwipeRefresh.isRefreshing = false
}

override fun showError(code: Int?, message: String?) {
    Toast.makeText(requireContext(), "$message $code", Toast.LENGTH_LONG).show()
}

override fun setPresenter(presenter: FileContracts.Presenter) {
    mPresenter = presenter
}


}
