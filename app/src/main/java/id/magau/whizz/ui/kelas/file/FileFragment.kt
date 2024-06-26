package id.magau.whizz.ui.kelas.file

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelFile
import id.magau.whizz.ui.kelas.KelasActivity
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.fragment_kurikulum.view.*
import kotlinx.android.synthetic.main.item_loading.view.*
import id.magau.whizz.utils.download


//
//
class FileFragment : Fragment(R.layout.fragment_kurikulum), FileContracts.View {


    companion object {
        const val KEY_ID_PRODUK = "ID_PRODUK"
        const val KEY_KELAS_SAYA = "KELAS_SAYA"
        //        const val KEY_TITLE = "TITLE"
//
        @JvmStatic
        fun newInstance(idProduct: String, kelasSaya: Boolean): FileFragment {
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
        requireArguments().getBoolean(KEY_KELAS_SAYA, false)
    }

    private lateinit var mView: View
    private lateinit var mPresenter: FileContracts.Presenter
    private var mAdater = AdapterFile()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mView = view
        FilePresenter(requireContext(), this)
        mPresenter.loadData(idProduct)

        mView.apply {
            tvTitle visibility false
            mRecyclerKurikulum.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = mAdater
            }
        }

        mAdater.setOnDownloadClickListener { title, uri ->
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    requireActivity(), arrayOf(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ),KelasActivity.PERMISSION_WRITE_EXTERNAL
                )
            } else {
                requireContext().download(uri!!)
            }
        }



    }
//
//    fun permission(){
//        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
//            != PackageManager.PERMISSION_GRANTED
//        ) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(
//                    requireActivity(),
//                    Manifest.permission.CAMERA
//                )
//            ) {
//                requestPermissions(
//                    requireActivity(),
//                    arrayOf(Manifest.permission.CAMERA),
//                    PERMISSION_IMAGE_CAPTURE
//                )
//            } else {
//                requestPermissions(
//                    this,
//                    arrayOf(Manifest.permission.CAMERA),
//                    PERMISSION_IMAGE_CAPTURE
//                )
//            }
//        } else {
//            dispatchTakePictureIntent()
//        }
//    }


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
