package id.magau.whizz.ui.event_detail.mentor

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelEvent
import id.magau.whizz.data.model.ModelMentor
import id.magau.whizz.utils.toast
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.fragment_mentor.view.*
import kotlinx.android.synthetic.main.item_loading.*

//
//
class MentorFragment : Fragment(R.layout.fragment_mentor),MentorFragmentContracts.View {

    companion object {
//        const val KEY_ID_PRODUK = "id"
//        const val KEY_TITLE = "TITLE"
//
        @JvmStatic
        fun newInstance(): MentorFragment {
            val args = Bundle()
//            args.putString(KEY_ID_PRODUK, id)
//            args.putString(KEY_TITLE, title)
            val fragment = MentorFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val mAdapter = AdapterMentor()
    private lateinit var mPresenter : MentorFragmentContracts.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.mRecyclerMentor.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
        val data = arrayListOf<ModelMentor?>(
            ModelMentor("Andi Tenroaji Ahmad","Hafiz",0,"Menjadi lebih baik, menjadi penghafal alquran, mengajak berbuat amar maruf nahi mungkar"),
            ModelMentor("Andi Tenroaji Ahmad","Hafiz",0,"Menjadi lebih baik, menjadi penghafal alquran, mengajak berbuat amar maruf nahi mungkar"),
            ModelMentor("Andi Tenroaji Ahmad","Hafiz",0,"Menjadi lebih baik, menjadi penghafal alquran, mengajak berbuat amar maruf nahi mungkar"),
            ModelMentor("Andi Tenroaji Ahmad","Hafiz",0,"Menjadi lebih baik, menjadi penghafal alquran, mengajak berbuat amar maruf nahi mungkar"),
            ModelMentor("Andi Tenroaji Ahmad","Hafiz",0,"Menjadi lebih baik, menjadi penghafal alquran, mengajak berbuat amar maruf nahi mungkar"),
            ModelMentor("Andi Tenroaji Ahmad","Hafiz",0,"Menjadi lebih baik, menjadi penghafal alquran, mengajak berbuat amar maruf nahi mungkar"),
            ModelMentor("Andi Tenroaji Ahmad","Hafiz",0,"Menjadi lebih baik, menjadi penghafal alquran, mengajak berbuat amar maruf nahi mungkar"),
            ModelMentor("Andi Tenroaji Ahmad","Hafiz",0,"Menjadi lebih baik, menjadi penghafal alquran, mengajak berbuat amar maruf nahi mungkar"),
            ModelMentor("Andi Tenroaji Ahmad","Hafiz",0,"Menjadi lebih baik, menjadi penghafal alquran, mengajak berbuat amar maruf nahi mungkar")
        )
        showMentor(data)
    }

    override fun showMentor(data: ArrayList<ModelMentor?>) {
        mAdapter.updateAdapter(data)
    }

    override fun showLoading(show: Boolean) {
        mProgresBar visibility show
    }

    override fun showError(code: Int?, message: String?) {
        toast("$code $message")
    }

    override fun setPresenter(presenter: MentorFragmentContracts.Presenter) {
        mPresenter = presenter
    }


//    override fun showBank(data: MutableList<ModelBank>) {
////        mAdapter.updateAdapter(data)
//    }

//    override fun showLoading(show: Boolean) {
////        mView.mProgresBar visibility show
////        if (mView.mSwipeRefresh.isRefreshing) mView.mSwipeRefresh.isRefreshing = false
//    }
//
//    override fun showError(code: Int?, message: String?) {
////        Toast.makeText(requireContext(), "$message $code", Toast.LENGTH_LONG).show()
//    }
//
//
//
//    override fun setPresenter(presenter: TokenContracts.Presenter) {
////        mPresenter = presenter
//    }

}
