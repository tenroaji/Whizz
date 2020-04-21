package id.magau.whizz.ui.event_detail.mentor

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelEvent
import id.magau.whizz.data.model.ModelMentor
import id.magau.whizz.data.model.ModelPemateri
import id.magau.whizz.utils.toast
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.fragment_mentor.view.*
import kotlinx.android.synthetic.main.item_loading.*
import java.io.Serializable

//
//
class MentorFragment : Fragment(R.layout.fragment_mentor),MentorFragmentContracts.View {

    companion object {
        const val KEY_PEMATERI = "pemateri"

        @JvmStatic
        fun newInstance(pemateri : List<ModelPemateri>): MentorFragment {
            val args = Bundle()
            args.putSerializable(KEY_PEMATERI, pemateri as Serializable)
//            args.putString(KEY_TITLE, title)
            val fragment = MentorFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val mAdapter = AdapterMentor()
    private lateinit var mPresenter : MentorFragmentContracts.Presenter
    private val mPemateri by lazy{
        arguments?.getSerializable(KEY_PEMATERI) as List<ModelPemateri>
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.mRecyclerMentor.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
//        val data = arrayListOf<ModelMentor?>(
//            ModelMentor("Andi Tenroaji Ahmad","Hafiz",0,"Menjadi lebih baik, menjadi penghafal alquran, mengajak berbuat amar maruf nahi mungkar"),
//            ModelMentor("Andi Tenroaji Ahmad","Hafiz",0,"Menjadi lebih baik, menjadi penghafal alquran, mengajak berbuat amar maruf nahi mungkar"),
//            ModelMentor("Andi Tenroaji Ahmad","Hafiz",0,"Menjadi lebih baik, menjadi penghafal alquran, mengajak berbuat amar maruf nahi mungkar"),
//            ModelMentor("Andi Tenroaji Ahmad","Hafiz",0,"Menjadi lebih baik, menjadi penghafal alquran, mengajak berbuat amar maruf nahi mungkar"),
//            ModelMentor("Andi Tenroaji Ahmad","Hafiz",0,"Menjadi lebih baik, menjadi penghafal alquran, mengajak berbuat amar maruf nahi mungkar"),
//            ModelMentor("Andi Tenroaji Ahmad","Hafiz",0,"Menjadi lebih baik, menjadi penghafal alquran, mengajak berbuat amar maruf nahi mungkar"),
//            ModelMentor("Andi Tenroaji Ahmad","Hafiz",0,"Menjadi lebih baik, menjadi penghafal alquran, mengajak berbuat amar maruf nahi mungkar"),
//            ModelMentor("Andi Tenroaji Ahmad","Hafiz",0,"Menjadi lebih baik, menjadi penghafal alquran, mengajak berbuat amar maruf nahi mungkar"),
//            ModelMentor("Andi Tenroaji Ahmad","Hafiz",0,"Menjadi lebih baik, menjadi penghafal alquran, mengajak berbuat amar maruf nahi mungkar")
//        )
        showMentor(mPemateri)
    }

    override fun showMentor(data: List<ModelPemateri?>) {
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
